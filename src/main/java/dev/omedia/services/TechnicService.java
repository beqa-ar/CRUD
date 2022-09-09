package dev.omedia.services;

import dev.omedia.domains.Technic;
import dev.omedia.enums.LoanStatus;
import dev.omedia.exceptions.EntityAlreadyExistsException;
import dev.omedia.exceptions.EntityNotFoundException;
import dev.omedia.repositories.TechnicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TechnicService {

    private final TechnicRepository repo;

    @Autowired
    public TechnicService(TechnicRepository repo) {
        this.repo = repo;
    }

    public Page<Technic> getTechnics(final Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Cacheable(cacheNames = "technic",key = "#id")
    public Technic getTechnic(final long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Technic.class.getName() + "with id: " + id + " not found"));
    }

    @CachePut(cacheNames = "technic",key = "#technic.id")
    public Technic addTechnic(final Technic technic) {
        if(repo.existsById(technic.getId())){
            throw new EntityAlreadyExistsException(Technic.class.getName() + "with id: " + technic.getId() + " already exists");
        }
        return repo.save(technic);
    }

    @CachePut(cacheNames = "technic",key = "#id")
    public Technic updateTechnic(final long id, final Technic technic) {
        if (repo.existsById(id)) {
            technic.setId(id);
            return repo.save(technic);
        } else {
            throw new EntityNotFoundException(Technic.class.getName() + "with id: " + id + " not found");
        }
    }

    @CacheEvict(cacheNames = "technic",key = "#id")
    public void removeTechnicById(final long id) {
        repo.deleteById(id);
    }

    public Page<Technic> getTechnicsByOwnerPersonalNo(final String ownerPersonalNo, final Pageable pageable) {
        return repo.findByOwnerPersonalNo(ownerPersonalNo, pageable);
    }

    public Page<Technic> getTechnicsByLoanStatus(final LoanStatus status, final Pageable pageable) {
        return repo.findByLoanStatus(status, pageable);
    }

}
