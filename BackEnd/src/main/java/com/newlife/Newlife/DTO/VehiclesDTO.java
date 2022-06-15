package com.newlife.Newlife.DTO;

import com.newlife.Newlife.entity.Vehicles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class VehiclesDTO {

    private Long id;
    private String apartment;
    private String plate;
    private String brand;
    private String model;
    private String color;

    public VehiclesDTO(Vehicles vehicle){
        this.id = vehicle.getId();
        this.apartment = vehicle.getApartment();
        this.plate = vehicle.getPlate();
        this.brand = vehicle.getBrand();
        this.model = vehicle.getModel();
        this.color = vehicle.getColor();
    }
}
