/**
 * Тесты для проверки операций CRUD на сущности Plant с использованием JPA репозитория.
 */
package org.example.stock;

import org.example.stock.model.Plant;
import org.example.stock.repository.PlantRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class PlantStockApplicationTests {

    @Autowired
    private PlantRepository repo;

    /**
     * Тест для добавления нового растения в базу данных.
     */
    @Test
    public void testAddNew() {
        Plant plant = new Plant();
        plant.setName("Aloe Vera");
        plant.setArticle("ALOE123");
        plant.setDescription("Medicinal plant");
        plant.setCategory("Succulents");
        plant.setPrice(new BigDecimal("5.99"));
        plant.setQuantity(50);

        Plant savedPlant = repo.save(plant);

        Assertions.assertThat(savedPlant).isNotNull();
        Assertions.assertThat(savedPlant.getId()).isGreaterThan(0);
    }

    /**
     * Тест для получения списка всех растений из базы данных.
     */
    @Test
    public void testListAll() {
        Iterable<Plant> plants = repo.findAll();
        Assertions.assertThat(plants).hasSizeGreaterThan(0);

        for (Plant plant : plants) {
            System.out.println(plant);
        }
    }

    /**
     * Тест для обновления цены растения в базе данных.
     */
    @Test
    public void testUpdate() {
        Long plantId = 1L; // Идентификатор для обновления
        Optional<Plant> optionalPlant = repo.findById(plantId);
        Plant plant = optionalPlant.get();
        plant.setPrice(new BigDecimal("6.99"));
        repo.save(plant);

        Plant updatedPlant = repo.findById(plantId).get();
        Assertions.assertThat(updatedPlant.getPrice()).isEqualTo(new BigDecimal("6.99"));
    }

    /**
     * Тест для получения растения по его идентификатору.
     */
    @Test
    public void testGet() {
        Long plantId = 1L; // Идентификатор для поиска
        Optional<Plant> optionalPlant = repo.findById(plantId);
        Assertions.assertThat(optionalPlant).isPresent();
        System.out.println(optionalPlant.get());
    }

    /**
     * Тест для удаления растения из базы данных.
     */
    @Test
    public void testDelete() {
        Long plantId = 1L; // Идентификатор для удаления
        repo.deleteById(plantId);

        Optional<Plant> optionalPlant = repo.findById(plantId);
        Assertions.assertThat(optionalPlant).isNotPresent();
    }
}
