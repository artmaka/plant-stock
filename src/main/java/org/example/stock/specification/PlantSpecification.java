package org.example.stock.specification;

import org.example.stock.model.Plant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/**
 * Спецификации для запросов к сущности Plant.
 */
public class PlantSpecification {

    /**
     * Возвращает спецификацию для поиска растений по имени, содержащему указанную подстроку.
     *
     * @param name Имя для поиска
     * @return Спецификация для поиска по имени
     */
    public static Specification<Plant> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    /**
     * Возвращает спецификацию для поиска растений по категории.
     *
     * @param category Категория для поиска
     * @return Спецификация для поиска по категории
     */
    public static Specification<Plant> hasCategory(String category) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category"), category);
    }

    /**
     * Возвращает спецификацию для поиска растений по артикулу.
     *
     * @param article Артикул для поиска
     * @return Спецификация для поиска по артикулу
     */
    public static Specification<Plant> hasArticle(String article) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("article"), article);
    }
}