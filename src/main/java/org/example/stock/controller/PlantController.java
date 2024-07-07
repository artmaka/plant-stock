package org.example.stock.controller;

import org.example.stock.model.Plant;
import org.example.stock.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/plants")
public class PlantController {
    private final PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping
    public String showPlantList(Model model) {
        List<Plant> listPlants = plantService.getAllPlants();
        model.addAttribute("listPlants", listPlants);
        return "plants";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("plant", new Plant());
        model.addAttribute("pageTitle", "Добавить позицию");
        return "plant_form";
    }

    @PostMapping("/save")
    public String savePlant(@ModelAttribute Plant plant, RedirectAttributes ra) {
        plantService.updatePlant(plant);
        ra.addFlashAttribute("message", "Товар успешно сохранен.");
        return "redirect:/plants";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Plant plant = plantService.getPlantById(id);
            model.addAttribute("plant", plant);
            model.addAttribute("pageTitle", "Edit Plant (ID: " + id + ")");
            return "plant_form";
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/plants";
        }
    }

    @GetMapping("/delete/{id}")
    public String deletePlant(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            plantService.deletePlant(id);
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/plants";
    }

    @GetMapping("/search")
    public String searchPlants(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String category,
                               @RequestParam(required = false) String article,
                               @RequestParam(defaultValue = "id") String sortBy,
                               @RequestParam(defaultValue = "asc") String sortOrder,
                               Model model) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        List<Plant> listPlants = plantService.searchPlants(name, category, article, sort);
        model.addAttribute("listPlants", listPlants);
        return "plants";
    }

    @GetMapping("/{id}")
    public String viewPlantDetail(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Plant plant = plantService.getPlantById(id);
            model.addAttribute("plant", plant);
            return "plant_detail";
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/plants";
        }
    }
}
