package dev.omedia.services;

import dev.omedia.domains.Material;
import dev.omedia.exceptions.EntityNotFoundException;
import dev.omedia.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    private final MaterialRepository repo;

    @Autowired
    public MaterialService(MaterialRepository repo) {
        this.repo = repo;
    }

    public Iterable<Material> getMaterials() {
        return repo.findAll();
    }

    public Material getMaterial(final long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Material.class.getName() + "with id: " + id + " not found"));
    }

    public Material addMaterial(final Material material) {
        return repo.save(material);
    }

    public Material updateMaterial(final long id, final Material material) {
        if (repo.existsById(id)) {
            material.setId(id);
            return repo.save(material);
        } else {
            throw new EntityNotFoundException(Material.class.getName() + "with id: " + id + " not found");
        }
    }

    public void removeMaterialById(final long id) {
        repo.deleteById(id);
    }

}
