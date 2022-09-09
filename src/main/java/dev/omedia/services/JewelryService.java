package dev.omedia.services;

import dev.omedia.domains.Jewelry;
import dev.omedia.enums.LoanStatus;
import dev.omedia.exceptions.EntityAlreadyExistsException;
import dev.omedia.exceptions.EntityNotFoundException;
import dev.omedia.repositories.JewelryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JewelryService {

    private final JewelryRepository repo;

    @Autowired
    public JewelryService(JewelryRepository repo) {
        this.repo = repo;
    }

    public Page<Jewelry> getJewelries(final Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Cacheable(cacheNames = "jewelry",key = "#id")
    public Jewelry getJewelry(final long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Jewelry.class.getName() + "with id: " + id + " not found"));
    }

    @CachePut(cacheNames = "jewelry",key = "#jewelry.id")
    public Jewelry addJewelry(final Jewelry jewelry) {

        if(repo.existsById(jewelry.getId())){
            throw new EntityAlreadyExistsException(Jewelry.class.getName() + "with id: " + jewelry.getId() + " already exists");
        }
        return repo.save(jewelry);
    }

    @CachePut(cacheNames = "jewelry",key = "#id")
    public Jewelry updateJewelry(final long id, final Jewelry jewelry) {
        if (repo.existsById(id)) {
            jewelry.setId(id);
            return repo.save(jewelry);
        } else {
            throw new EntityNotFoundException(Jewelry.class.getName() + "with id: " + id + " not found");
        }
    }

    @CacheEvict(cacheNames = "jewelry",key = "#id")
    public void removeJewelryById(final long id) {
        repo.deleteById(id);
    }

    public Page<Jewelry> getJewelriesByOwnerPersonalNo(final String ownerPersonalNo, final Pageable pageable) {
        return repo.findByOwnerPersonalNo(ownerPersonalNo, pageable);
    }

    public Page<Jewelry> getJewelriesByLoanStatus(final LoanStatus status, final Pageable pageable) {
        return repo.findByLoanStatus(status, pageable);
    }

}
