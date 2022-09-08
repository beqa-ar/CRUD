package dev.omedia.repositories;

import dev.omedia.domains.Jewelry;
import dev.omedia.enums.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JewelryRepository extends PagingAndSortingRepository<Jewelry,Long> {
    Page<Jewelry> findByOwnerPersonalNo(String ownerPersonalNo, Pageable pageable);
    Page<Jewelry> findByLoanStatus(LoanStatus status, Pageable pageable);
}