package org.example.stock.service;

import org.example.stock.model.Plant;
import org.example.stock.repository.PlantRepository;
import org.example.stock.specification.PlantSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlantService {
    private final PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public Plant getPlantById(Long id) {
        return plantRepository.findById(id).orElseThrow(() -> new RuntimeException("Plant not found"));
    }

    public Plant savePlant(Plant plant) {
        return plantRepository.save(plant);
    }

    public Plant updatePlant(Plant plant) {
        Plant existingPlant = plantRepository.findById(plant.getId()).orElseThrow(() -> new RuntimeException("Plant not found"));

        if (!existingPlant.getQuantity().equals(plant.getQuantity())) {
            plant.setLastQuantityChange(LocalDateTime.now());
        }

        return plantRepository.save(plant);
    }

    public void deletePlant(Long id) {
        plantRepository.deleteById(id);
    }

    public List<Plant> searchPlants(String name, String category, String article, Sort sort) {
        Specification<Plant> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and(PlantSpecification.hasName(name));
        }
        if (category != null && !category.isEmpty()) {
            spec = spec.and(PlantSpecification.hasCategory(category));
        }
        if (article != null && !article.isEmpty()) {
            spec = spec.and(PlantSpecification.hasArticle(article));
        }

        return plantRepository.findAll(spec, sort);
    }
}
