package dev.omedia.repositories;

import dev.omedia.domains.Automobile;
import dev.omedia.domains.Technic;
import dev.omedia.enums.LoanStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicRepository extends CrudRepository<Technic,Long> {
    Iterable<Technic> findByOwnerPersonalNo(String ownerPersonalNo);
    Iterable<Technic> findByStatus(LoanStatus status);
}
