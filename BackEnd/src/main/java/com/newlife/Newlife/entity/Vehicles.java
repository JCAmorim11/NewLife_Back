package com.newlife.Newlife.entity;

import com.newlife.Newlife.DTO.VehiclesDTO;
import lombok.*;
import javax.persistence.*;


@Entity
@Table(name = "tblVehicles")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVehicles")
    private long id;

    @Column(name = "idLocation")
    public String apartment;

    @Column(name = "dsPlate")
    public String plate;

    @Column(name = "dsBrand")
    public String brand;

    @Column(name = "dsModel")
    public String model;

    @Column(name = "dsColor")
    public String color;

    public Vehicles(VehiclesDTO dto){
        this.id = 0;
        this.apartment = dto.getApartment();
        this.plate = dto.getPlate();
        this.brand = dto.getBrand();
        this.model = dto.getModel();
        this.color = dto.getColor();
    }


    public void updateRegistry(VehiclesDTO dto){
       this.plate = dto.getPlate();
       this.brand = dto.getBrand();
       this.model = dto.getModel();
       this.color = dto.getColor();
       this.apartment = dto.getApartment();
    }
}
