package com.example.CarsApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListResponseDTO {
    private boolean isDarkMode;
    private List<CarDTO> carsList;
}
