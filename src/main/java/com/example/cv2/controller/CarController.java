package com.example.cv2.controller;

import com.example.cv2.model.Car;
import com.example.cv2.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("cars", carService.getAllCars());

        return "list";
    }

    @GetMapping("/detail/{index}")
    public String detail(Model model, @PathVariable int index) {
        Car car = carService.getCarById(index);
        if(car != null) {
            model.addAttribute("car", car);

            return "detail";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("edit", false);

        return "edit";
    }

    @GetMapping("/edit/{index}")
    public String edit(Model model, @PathVariable int index) {
        System.out.println(index);
        Car car = carService.getCarById(index);
        if(car != null) {
            car.setId(index);

            model.addAttribute("car", car);
            model.addAttribute("edit", true);

            return "edit";
        }

        return "redirect:/";
    }

    @GetMapping("/delete/{index}")
    public String delete(Model model, @PathVariable int index) {
        carService.deleteCar(index);

        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute Car car) {
        // validace... skipped

        // Long gug = Long.valueOf(45);
        // System.out.println(gug.longValue());

        carService.saveCar(car);
        return "redirect:/";
    }
}
