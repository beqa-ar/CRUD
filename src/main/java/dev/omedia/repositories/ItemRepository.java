package dev.omedia.repositories;

import dev.omedia.domains.Item;
import dev.omedia.enums.LoanStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {
    Iterable<Item> findByOwnerPersonalNo(String ownerPersonalNo);
    Iterable<Item> findByStatus(LoanStatus status);
}
