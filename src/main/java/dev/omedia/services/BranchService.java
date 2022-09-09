package dev.omedia.services;

import dev.omedia.domains.Branch;
import dev.omedia.exceptions.EntityAlreadyExistsException;
import dev.omedia.exceptions.EntityNotFoundException;
import dev.omedia.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    private final BranchRepository repo;

    @Autowired
    public BranchService(BranchRepository repo) {
        this.repo = repo;
    }

    public Page<Branch> getBranches(final Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Cacheable(cacheNames = "branch",key = "#id")
    public Branch getBranch(final long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Branch.class.getName() + "with id: " + id + " not found"));
    }

    @CachePut(cacheNames = "branch",key = "#branch.id")
    public Branch addBranch(final Branch branch) {
        if(repo.existsById(branch.getId())){
            throw new EntityAlreadyExistsException(Branch.class.getName() + "with id: " + branch.getId() + " already exists");
        }
        return repo.save(branch);
    }

    @CachePut(cacheNames = "branch",key = "#id")
    public Branch updateBranch(final long id, final Branch branch) {
        if (repo.existsById(id)) {
            branch.setId(id);
            return repo.save(branch);
        } else {
            throw new EntityNotFoundException(Branch.class.getName() + "with id: " + id + " not found");
        }
    }

    @CacheEvict(cacheNames = "branch",key = "#id")
    public void removeBranchById(final long id) {
        repo.deleteById(id);
    }

}
