package dev.omedia.repositories;

import dev.omedia.domains.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends CrudRepository<Branch,Long> {
}
