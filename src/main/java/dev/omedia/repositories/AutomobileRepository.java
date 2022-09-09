package dev.omedia.repositories;

import dev.omedia.domains.Automobile;
import dev.omedia.enums.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomobileRepository extends PagingAndSortingRepository<Automobile,Long> {
    Page<Automobile> findByOwnerPersonalNo(String ownerPersonalNo, Pageable pageable);
    Page<Automobile> findByLoanStatus(LoanStatus status, Pageable pageable);
}
