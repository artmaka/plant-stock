package org.example.stock.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Сущность, представляющая растение в системе склада.
 */
@Entity
@Table(name = "plants")
public class Plant {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "article", unique = true, nullable = false)
    private String article;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    @Getter
    @Setter
    @Column(name = "category")
    private String category;

    @Getter
    @Setter
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Getter
    @Setter
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Getter
    @Setter
    @Column(name = "last_quantity_change")
    private LocalDateTime lastQuantityChange;

    @Getter
    @Setter
    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

}
