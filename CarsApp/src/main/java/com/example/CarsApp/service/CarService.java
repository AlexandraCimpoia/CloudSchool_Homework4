package com.example.CarsApp.service;

import com.example.CarsApp.dto.CarDTO;
import com.example.CarsApp.model.Car;
import com.example.CarsApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<CarDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarDTO> result = new ArrayList<>();
        for (Car c : cars) {
            result.add(new CarDTO(c.getName(), c.getModel()));
        }
        return result;
    }

    public Car saveCar(Car c){
        return carRepository.save(c);
    }
}
