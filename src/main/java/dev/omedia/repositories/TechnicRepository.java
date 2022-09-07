package dev.omedia.repositories;

import dev.omedia.domains.Technic;
import dev.omedia.enums.LoanStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicRepository extends PagingAndSortingRepository<Technic,Long> {
    List<Technic> findByOwnerPersonalNo(String ownerPersonalNo, Pageable pageable);
    List<Technic> findByStatus(LoanStatus status, Pageable pageable);
}
