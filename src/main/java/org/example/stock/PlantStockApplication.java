package org.example.stock;

import org.example.stock.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс приложения для управления запуском и конфигурацией приложения склада растений.
 */
@SpringBootApplication
public class PlantStockApplication {

    private final PlantService plantService;

    /**
     * Конструктор главного класса приложения для внедрения зависимости от сервиса растений.
     *
     * @param plantService Сервис управления данными о растениях
     */
    @Autowired
    public PlantStockApplication(PlantService plantService) {
        this.plantService = plantService;
    }

    /**
     * Основной метод, запускающий приложение Spring Boot.
     *
     * @param args Аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(PlantStockApplication.class, args);
        System.out.print("Done\n");
    }
}
