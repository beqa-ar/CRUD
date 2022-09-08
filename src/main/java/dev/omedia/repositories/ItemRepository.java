package dev.omedia.repositories;

import dev.omedia.domains.Item;
import dev.omedia.enums.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item,Long> {
    Page<Item> findByOwnerPersonalNo(String ownerPersonalNo, Pageable pageable);
    Page<Item> findByLoanStatus(LoanStatus status, Pageable pageable);
}
