package dev.omedia.services;

import dev.omedia.domains.Automobile;
import dev.omedia.domains.Item;
import dev.omedia.enums.LoanStatus;
import dev.omedia.exceptions.EntityAlreadyExistsException;
import dev.omedia.exceptions.EntityNotFoundException;
import dev.omedia.repositories.AutomobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutomobileService {

    private final AutomobileRepository repo;

    @Autowired
    public AutomobileService(AutomobileRepository repo) {
        this.repo = repo;
    }

    public Iterable<Automobile> getAutomobiles() {
        return repo.findAll();
    }

    public Automobile getAutomobile(final long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Automobile.class.getName()+"with id: "+id+" not found" ));
    }

    public Automobile addAutomobile(final Automobile automobile) {
            return repo.save(automobile);
    }

    public Automobile updateAutomobile(final long id,final Automobile automobile) {
        if (repo.existsById(id)) {
            automobile.setId(id);
            return repo.save(automobile);
        } else {
            throw new EntityNotFoundException(Automobile.class.getName() + "with id: " + id + " not found");
        }
    }
    public void removeAutomobileById(final long id) {
        repo.deleteById(id);
    }
    public Iterable<Automobile> getItemByOwnerPersonalNo(final String ownerPersonalNo){
        return repo.findByOwnerPersonalNo(ownerPersonalNo);
    }
    public Iterable<Automobile> getItemByStatus(final LoanStatus status){
        return repo.findByStatus(status);
    }

}
