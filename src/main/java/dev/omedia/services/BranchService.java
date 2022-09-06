package dev.omedia.services;

import dev.omedia.domains.Branch;
import dev.omedia.enums.LoanStatus;
import dev.omedia.exceptions.EntityNotFoundException;
import dev.omedia.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    private final BranchRepository repo;

    @Autowired
    public BranchService(BranchRepository repo) {
        this.repo = repo;
    }

    public Iterable<Branch> getJewelries() {
        return repo.findAll();
    }

    public Branch getBranch(final long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Branch.class.getName() + "with id: " + id + " not found"));
    }

    public Branch addBranch(final Branch branch) {
        return repo.save(branch);
    }

    public Branch updateBranch(final long id, final Branch branch) {
        if (repo.existsById(id)) {
            branch.setId(id);
            return repo.save(branch);
        } else {
            throw new EntityNotFoundException(Branch.class.getName() + "with id: " + id + " not found");
        }
    }

    public void removeBranchById(final long id) {
        repo.deleteById(id);
    }

}
