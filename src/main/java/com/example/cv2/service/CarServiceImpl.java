package com.example.cv2.service;

import com.example.cv2.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    protected ArrayList<Car> cars = new ArrayList<>();

    @Override
    public ArrayList<Car> getAllCars() {
        return cars;
    }

    @Override
    public Car getCarById(int id) {
        Car car = null;
        if(id > -1 && id < getCount()) {
            car = cars.get(id);
        }

        return car;
    }

    @Override
    public void saveCar(Car car) {
        if(car.getId() > -1 && car.getId() < getCount()) {
            cars.remove((int) car.getId());
        }

        cars.add(car);
    }

    @Override
    public void deleteCar(int id) {
        if(id > -1 && id < getCount()) {
            cars.remove(id);
        }
    }

    @Override
    public int getCount() {
        return cars.size();
    }
}
