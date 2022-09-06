package dev.omedia.services;

import dev.omedia.domains.Jewelry;
import dev.omedia.enums.LoanStatus;
import dev.omedia.exceptions.EntityNotFoundException;
import dev.omedia.repositories.JewelryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JewelryService {

    private final JewelryRepository repo;

    @Autowired
    public JewelryService(JewelryRepository repo) {
        this.repo = repo;
    }

    public Iterable<Jewelry> getJewelries() {
        return repo.findAll();
    }

    public Jewelry getJewelry(final long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Jewelry.class.getName() + "with id: " + id + " not found"));
    }

    public Jewelry addJewelry(final Jewelry jewelry) {
        return repo.save(jewelry);
    }

    public Jewelry updateJewelry(final long id, final Jewelry jewelry) {
        if (repo.existsById(id)) {
            jewelry.setId(id);
            return repo.save(jewelry);
        } else {
            throw new EntityNotFoundException(Jewelry.class.getName() + "with id: " + id + " not found");
        }
    }

    public void removeJewelryById(final long id) {
        repo.deleteById(id);
    }

    public Iterable<Jewelry> getItemByOwnerPersonalNo(final String ownerPersonalNo) {
        return repo.findByOwnerPersonalNo(ownerPersonalNo);
    }

    public Iterable<Jewelry> getItemByStatus(final LoanStatus status) {
        return repo.findByStatus(status);
    }

}
