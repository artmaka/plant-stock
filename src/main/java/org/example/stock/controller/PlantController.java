package org.example.stock.controller;

import org.example.stock.model.Plant;
import org.example.stock.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Контроллер для управления растениями в системе склада.
 */
@Controller
@RequestMapping("/plants")
public class PlantController {

    private final PlantService plantService;

    /**
     * Конструктор контроллера для внедрения зависимости.
     *
     * @param plantService Сервис растений
     */
    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    /**
     * Отображает список всех растений (далее позиций).
     *
     * @param model Модель представления для передачи данных в представление
     * @return Имя представления "plants"
     */
    @GetMapping
    public String showPlantList(Model model) {
        List<Plant> listPlants = plantService.getAllPlants();
        model.addAttribute("listPlants", listPlants);
        return "plants";
    }

    /**
     * Отображает форму для добавления новой позиции.
     *
     * @param model Модель представления для передачи данных в представление
     * @return Имя представления "plant_form"
     */
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("plant", new Plant());
        model.addAttribute("pageTitle", "Добавить позицию");
        return "plant_form";
    }

    /**
     * Сохранение позиции.
     *
     * @param plant Данные растения для сохранения
     * @param ra    Редирект атрибуты для передачи сообщений между запросами
     * @return Редирект на список позиций
     */
    @PostMapping("/saved")
    public String savePlant(@ModelAttribute Plant plant, RedirectAttributes ra) {
        if (plant.getId() == null) {
            plantService.savePlant(plant);
            ra.addFlashAttribute("message", "Товар успешно добавлен.");
        } else {
            plantService.updatePlant(plant);
            ra.addFlashAttribute("message", "Товар успешно сохранен.");
        }
        return "redirect:/plants";
    }

    /**
     * Обрабатывает создание новой позиции.
     *
     * @param plant Данные растения для создания
     * @param ra    Редирект атрибуты для передачи сообщений между запросами
     * @return Редирект на список позиций
     */
    @PostMapping("/created")
    public String createPlant(@ModelAttribute Plant plant, RedirectAttributes ra) {
        plantService.savePlant(plant);
        ra.addFlashAttribute("message", "Товар успешно добавлен.");
        return "redirect:/plants";
    }

    /**
     * Отображает форму для редактирования позиции по его идентификатору.
     *
     * @param id    Идентификатор позиции для редактирования
     * @param model Модель представления для передачи данных в представление
     * @param ra    Редирект атрибуты для передачи сообщений между запросами
     * @return Имя представления "plant_form" для редактирования или редирект на список позиций
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Plant plant = plantService.getPlantById(id);
            model.addAttribute("plant", plant);
            model.addAttribute("pageTitle", "Изменение позиции");
            return "plant_form";
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/plants";
        }
    }

    /**
     * Удаляет позицию по его идентификатору.
     *
     * @param id Идентификатор позиции для удаления
     * @param ra Редирект атрибуты для передачи сообщений между запросами
     * @return Редирект на список позиций
     */
    @GetMapping("/delete/{id}")
    public String deletePlant(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            plantService.deletePlant(id);
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/plants";
    }

    /**
     * Осуществляет поиск позиций по заданным параметрам.
     *
     * @param name     Имя позиций для поиска
     * @param category Категория растения для поиска
     * @param article  Артикул растения для поиска
     * @param sortBy   Поле для сортировки списка растений (по умолчанию "id")
     * @param sortOrder Направление сортировки (по умолчанию "asc")
     * @param model    Модель представления для передачи данных в представление
     * @return Имя представления "plants" с результатами поиска
     */
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

    /**
     * Отображает детали позиции по его идентификатору.
     *
     * @param id    Идентификатор позиции для отображения деталей
     * @param model Модель представления для передачи данных в представление
     * @param ra    Редирект атрибуты для передачи сообщений между запросами
     * @return Имя представления "plant_detail" с деталями позиции или редирект на список позиций
     */
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
