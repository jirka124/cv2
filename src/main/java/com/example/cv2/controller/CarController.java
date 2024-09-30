package com.example.cv2.controller;

import com.example.cv2.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    private List<Car> cars = new ArrayList<>();

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("cars", cars);

        return "list";
    }

    @GetMapping("/detail/{index}")
    public String detail(Model model, @PathVariable int index) {
        if(index > -1 && index < cars.size()) {
            Car car = cars.get(index);
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
        if(index > -1 && index < cars.size()) {
            Car car = cars.get(index);
            car.setId(index);

            model.addAttribute("car", cars.get(index));
            model.addAttribute("edit", true);

            return "edit";
        }

        return "redirect:/";
    }

    @GetMapping("/delete/{index}")
    public String delete(Model model, @PathVariable int index) {
        if(index > -1 && index < cars.size()) {
            cars.remove(index);
        }

        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute Car car) {
        // validace... skipped

        // Long gug = Long.valueOf(45);
        // System.out.println(gug.longValue());

        if(car.getId() > -1 && car.getId() < cars.size()) {
            cars.remove((int) car.getId());
        }

        cars.add(car);
        return "redirect:/";
    }
}
