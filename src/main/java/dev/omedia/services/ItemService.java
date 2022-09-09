package dev.omedia.services;

import dev.omedia.domains.Item;
import dev.omedia.enums.LoanStatus;
import dev.omedia.exceptions.EntityAlreadyExistsException;
import dev.omedia.exceptions.EntityNotFoundException;
import dev.omedia.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ItemService {

    private final ItemRepository repo;

    @Autowired
    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    public Page<Item> getItems(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Cacheable(cacheNames = "item",key = "#id")
    public Item getItem(final long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Item.class.getName() + "with id: " + id + " not found"));
    }

    @CachePut(cacheNames = "item",key = "#item.id")
    public Item addItem(final Item item) {
        if(repo.existsById(item.getId())){
            throw new EntityAlreadyExistsException(Item.class.getName() + "with id: " + item.getId() + " already exists");
        }
        return repo.save(item);
    }

    @CachePut(cacheNames = "item",key = "#id")
    public Item updateItem(final long id, final Item item) {
        if (repo.existsById(id)) {
            item.setId(id);
            return repo.save(item);
        } else {
            throw new EntityNotFoundException(Item.class.getName() + "with id: " + id + " not found");
        }
    }

    @CacheEvict(cacheNames = "item",key = "#id")
    public void removeItemById(final long id) {
        repo.deleteById(id);
    }

    public Page<Item> getItemsByOwnerPersonalNo(final String ownerPersonalNo, final Pageable pageable) {
        return repo.findByOwnerPersonalNo(ownerPersonalNo, pageable);
    }

    public Page<Item> getItemsByLoanStatus(final LoanStatus status, final Pageable pageable) {
        return repo.findByLoanStatus(status, pageable);
    }

}
