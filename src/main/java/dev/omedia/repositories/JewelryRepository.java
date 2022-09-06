package dev.omedia.repositories;

import dev.omedia.domains.Automobile;
import dev.omedia.domains.Jewelry;
import dev.omedia.enums.LoanStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JewelryRepository extends CrudRepository<Jewelry,Long> {
    Iterable<Jewelry> findByOwnerPersonalNo(String ownerPersonalNo);
    Iterable<Jewelry> findByStatus(LoanStatus status);
}