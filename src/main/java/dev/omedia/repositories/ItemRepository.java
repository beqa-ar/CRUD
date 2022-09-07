package dev.omedia.repositories;

import dev.omedia.domains.Item;
import dev.omedia.enums.LoanStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item,Long> {
    List<Item> findByOwnerPersonalNo(String ownerPersonalNo, Pageable pageable);
    List<Item> findByStatus(LoanStatus status, Pageable pageable);
}
