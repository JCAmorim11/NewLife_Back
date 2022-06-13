package com.newlife.Newlife.DTO;

import com.newlife.Newlife.entity.Vehicles;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class VehiclesDTO {
    public String plate;
    public String brand;
    public String model;
    public String color;

    public VehiclesDTO(Vehicles vehicle){
        this.plate = vehicle.getPlate();
        this.brand = vehicle.getBrand();
        this.model = vehicle.getModel();
        this.color = vehicle.getColor();
    }
}
