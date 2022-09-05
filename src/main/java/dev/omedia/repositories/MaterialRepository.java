package dev.omedia.repositories;

import dev.omedia.domains.Material;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends CrudRepository<Material,Long> {
}
