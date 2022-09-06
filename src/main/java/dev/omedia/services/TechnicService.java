package dev.omedia.services;

import dev.omedia.domains.Technic;
import dev.omedia.enums.LoanStatus;
import dev.omedia.exceptions.EntityNotFoundException;
import dev.omedia.repositories.TechnicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnicService {

    private final TechnicRepository repo;

    @Autowired
    public TechnicService(TechnicRepository repo) {
        this.repo = repo;
    }

    public Iterable<Technic> getTechnics() {
        return repo.findAll();
    }

    public Technic getTechnic(final long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Technic.class.getName()+"with id: "+id+" not found" ));
    }

    public Technic addTechnic(final Technic technic) {
            return repo.save(technic);
    }

    public Technic updateTechnic(final long id,final Technic technic) {
        if (repo.existsById(id)) {
            technic.setId(id);
            return repo.save(technic);
        } else {
            throw new EntityNotFoundException(Technic.class.getName() + "with id: " + id + " not found");
        }
    }
    public void removeTechnicById(final long id) {
        repo.deleteById(id);
    }
    public Iterable<Technic> getItemByOwnerPersonalNo(final String ownerPersonalNo){
        return repo.findByOwnerPersonalNo(ownerPersonalNo);
    }
    public Iterable<Technic> getItemByStatus(final LoanStatus status){
        return repo.findByStatus(status);
    }

}
