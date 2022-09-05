package dev.omedia.repositories;

import dev.omedia.domains.Jewelry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JewelryRepository extends CrudRepository<Jewelry,Long> {
}