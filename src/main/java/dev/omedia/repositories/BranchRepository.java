package dev.omedia.repositories;

import dev.omedia.domains.Branch;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends PagingAndSortingRepository<Branch,Long> {
}
