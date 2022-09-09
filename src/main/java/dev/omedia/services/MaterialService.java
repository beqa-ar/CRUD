package dev.omedia.services;

import dev.omedia.domains.Material;
import dev.omedia.exceptions.EntityAlreadyExistsException;
import dev.omedia.exceptions.EntityNotFoundException;
import dev.omedia.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    private final MaterialRepository repo;

    @Autowired
    public MaterialService(MaterialRepository repo) {
        this.repo = repo;
    }

    public Page<Material> getMaterials(final Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Cacheable(cacheNames = "material", key = "#id")
    public Material getMaterial(final long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Material.class.getName() + "with id: " + id + " not found"));
    }

    @CachePut(cacheNames = "material", key = "#material.id")
    public Material addMaterial(final Material material) {
        if (repo.existsById(material.getId())) {
            throw new EntityAlreadyExistsException(Material.class.getName() + "with id: " + material.getId() + " already exists");
        }
        return repo.save(material);
    }

    @CachePut(cacheNames = "material",key = "#id")
    public Material updateMaterial(final long id, final Material material) {
        if (repo.existsById(id)) {
            material.setId(id);
            return repo.save(material);
        } else {
            throw new EntityNotFoundException(Material.class.getName() + "with id: " + id + " not found");
        }
    }

    @CacheEvict(cacheNames = "material",key = "#id")
    public void removeMaterialById(final long id) {
        repo.deleteById(id);
    }

}
