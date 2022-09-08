package dev.omedia.repositories;

import dev.omedia.domains.Technic;
import dev.omedia.enums.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicRepository extends PagingAndSortingRepository<Technic,Long> {
    Page<Technic> findByOwnerPersonalNo(String ownerPersonalNo, Pageable pageable);
    Page<Technic> findByLoanStatus(LoanStatus status, Pageable pageable);
}
