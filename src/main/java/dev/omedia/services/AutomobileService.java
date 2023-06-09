package dev.omedia.services;

import dev.omedia.domains.Automobile;
import dev.omedia.enums.LoanStatus;
import dev.omedia.exceptions.EntityAlreadyExistsException;
import dev.omedia.exceptions.EntityNotFoundException;
import dev.omedia.repositories.AutomobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AutomobileService {

    private final AutomobileRepository repo;

    @Autowired
    public AutomobileService(AutomobileRepository repo) {
        this.repo = repo;
    }


    public Page<Automobile> getAutomobiles(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Cacheable(cacheNames = "automobiles",key = "#id")
    public Automobile getAutomobile(final long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Automobile.class.getName() + "with id: " + id + " not found"));
    }

    @CachePut(cacheNames = "automobiles",key = "#automobile.id")
    public Automobile addAutomobile(final Automobile automobile) {
        if(repo.existsById(automobile.getId())){
            throw new EntityAlreadyExistsException(Automobile.class.getName() + "with id: " + automobile.getId() + " already exists");
        }
        return repo.save(automobile);
    }

    @CachePut(cacheNames = "automobiles",key = "#id")
    public Automobile updateAutomobile(final long id, final Automobile automobile) {
        if (repo.existsById(id)) {
            automobile.setId(id);
            return repo.save(automobile);
        } else {
            throw new EntityNotFoundException(Automobile.class.getName() + "with id: " + id + " not found");
        }
    }

    @CacheEvict(cacheNames = "automobiles",key = "#id")
    public void removeAutomobileById(final long id) {
        repo.deleteById(id);
    }

    public Page<Automobile> getAutomobilesByOwnerPersonalNo(final String ownerPersonalNo, final Pageable pageable) {
        return repo.findByOwnerPersonalNo(ownerPersonalNo, pageable);
    }

    public Page<Automobile> getAutomobilesByLoanStatus(final LoanStatus status, final Pageable pageable) {
        return repo.findByLoanStatus(status, pageable);
    }

}
