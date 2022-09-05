package dev.omedia.repositories;

import dev.omedia.domains.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {
}
