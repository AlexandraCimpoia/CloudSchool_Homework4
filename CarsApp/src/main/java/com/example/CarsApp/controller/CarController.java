package com.example.CarsApp.controller;

import com.example.CarsApp.dto.CarDTO;
import com.example.CarsApp.dto.DarkModeDTO;
import com.example.CarsApp.dto.CarListResponseDTO;
import com.example.CarsApp.model.Car;
import com.example.CarsApp.service.CarService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public CarListResponseDTO getAllCars(@CookieValue(value = "darkMode", defaultValue = "false") String darkMode) {
        boolean isDarkMode = Boolean.parseBoolean(darkMode);
        List<CarDTO> carsList = carService.getAllCars();
        return new CarListResponseDTO(isDarkMode, carsList);
    }

    @PutMapping("/dark-mode")
    public ResponseEntity<String> setDarkMode (@RequestBody DarkModeDTO darkModeDTO, HttpServletResponse response) {
        Cookie cookie = new Cookie("darkMode", String.valueOf(darkModeDTO.isDarkMode()));
        response.addCookie(cookie);
        return ResponseEntity.ok("Dark mode preference set successfully");
    }

    @PostMapping("/createCar")
    ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) {
        Car newCar = new Car();
        newCar.setName(carDTO.getName());
        newCar.setModel(carDTO.getModel());
        Car savedCar = carService.saveCar(newCar);
        CarDTO savedCarDTO = new CarDTO(savedCar.getName(), savedCar.getModel());
        return new ResponseEntity<>(savedCarDTO, HttpStatus.CREATED);
    }

}
