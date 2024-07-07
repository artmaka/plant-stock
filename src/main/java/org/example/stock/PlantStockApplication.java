package org.example.stock;
import org.example.stock.model.Plant;
import org.example.stock.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class PlantStockApplication {
    private final PlantService plantService;

    @Autowired
    public PlantStockApplication(PlantService plantService) {
        this.plantService = plantService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PlantStockApplication.class, args);
        System.out.print("Done\n");
    }

    /*@Bean
    public CommandLineRunner demo() {
        return args -> {
            Plant plant = new Plant();
            plant.setName("Роза красная");
            plant.setArticle("011");
            plant.setDescription("Красивая красная роза");
            plant.setCategory("Садовые растения");
            plant.setPrice(BigDecimal.valueOf(12.99));
            plant.setQuantity(50);
            plant.setLastQuantityChange(LocalDateTime.now());
            plantService.createPlant(plant);

            Plant savedPlant = plantService.getPlantById(plant.getId());
            System.out.println("Saved Plant: " + savedPlant);
        };
    }*/
}
