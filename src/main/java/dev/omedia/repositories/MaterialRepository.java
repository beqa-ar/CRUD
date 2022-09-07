package dev.omedia.repositories;

import dev.omedia.domains.Material;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends PagingAndSortingRepository<Material,Long> {
}
