package org.example.stock.specification;

import org.example.stock.model.Plant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class PlantSpecification {

    public static Specification<Plant> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Plant> hasCategory(String category) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<Plant> hasArticle(String article) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("article"), article);
    }

    public static Specification<Plant> priceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }

    public static Specification<Plant> quantityBetween(Integer minQuantity, Integer maxQuantity) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("quantity"), minQuantity, maxQuantity);
    }
}
