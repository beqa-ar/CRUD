package dev.omedia.repositories;

import dev.omedia.domains.Automobile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomobileRepository extends CrudRepository<Automobile,Long> {
}
