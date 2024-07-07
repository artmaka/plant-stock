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

/**
 * Сервис для выполнения операций с растениями в системе склада.
 */
@Service
public class PlantService {

    private final PlantRepository plantRepository;

    /**
     * Конструктор сервиса для внедрения зависимости репозитория растений.
     *
     * @param plantRepository Репозиторий растений
     */
    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    /**
     * Получает список всех растений.
     *
     * @return Список всех растений
     */
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    /**
     * Получает растение по его идентификатору.
     *
     * @param id Идентификатор растения
     * @return Растение с указанным идентификатором
     * @throws RuntimeException если растение не найдено
     */
    public Plant getPlantById(Long id) {
        return plantRepository.findById(id).orElseThrow(() -> new RuntimeException("Plant not found"));
    }

    /**
     * Сохраняет новое растение или обновляет существующее.
     *
     * @param plant Данные растения для сохранения или обновления
     * @return Сохраненное или обновленное растение
     */
    public Plant savePlant(Plant plant) {
        return plantRepository.save(plant);
    }

    /**
     * Обновляет данные существующего растения.
     * Если изменено количество, обновляет дату последнего изменения количества.
     *
     * @param plant Данные растения для обновления
     * @return Обновленное растение
     * @throws RuntimeException если растение не найдено
     */
    public Plant updatePlant(Plant plant) {
        Plant existingPlant = plantRepository.findById(plant.getId()).orElseThrow(() -> new RuntimeException("Plant not found"));

        if (!existingPlant.getQuantity().equals(plant.getQuantity())) {
            plant.setLastQuantityChange(LocalDateTime.now());
        }

        return plantRepository.save(plant);
    }

    /**
     * Удаляет растение по его идентификатору.
     *
     * @param id Идентификатор растения для удаления
     */
    public void deletePlant(Long id) {
        plantRepository.deleteById(id);
    }

    /**
     * Выполняет поиск растений по заданным критериям.
     *
     * @param name     Имя растения для поиска
     * @param category Категория растения для поиска
     * @param article  Артикул растения для поиска
     * @param sort     Сортировка результатов
     * @return Список растений, удовлетворяющих критериям поиска
     */
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
