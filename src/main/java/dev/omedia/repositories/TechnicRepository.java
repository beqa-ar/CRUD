package dev.omedia.repositories;

import dev.omedia.domains.Technic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicRepository extends CrudRepository<Technic,Long> {
}
