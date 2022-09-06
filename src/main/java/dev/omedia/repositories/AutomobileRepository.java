package dev.omedia.repositories;

import dev.omedia.domains.Automobile;
import dev.omedia.domains.Item;
import dev.omedia.enums.LoanStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomobileRepository extends CrudRepository<Automobile,Long> {
    Iterable<Automobile> findByOwnerPersonalNo(String ownerPersonalNo);
    Iterable<Automobile> findByStatus(LoanStatus status);
}
