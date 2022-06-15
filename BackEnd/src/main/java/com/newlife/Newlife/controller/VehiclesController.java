package com.newlife.Newlife.controller;

import com.newlife.Newlife.DTO.VehiclesDTO;
import com.newlife.Newlife.entity.Vehicles;
import com.newlife.Newlife.service.VehiclesService;
import lombok.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@RestController
@RequestMapping("vehicles")
@AllArgsConstructor
public class VehiclesController {

    private final VehiclesService vehiclesService;

    @GetMapping("/{plate}")
    public ResponseEntity<?> getUserById(@PathVariable String plate){
       VehiclesDTO dto = this.vehiclesService.findByPlate(plate);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    @PostMapping()
    @Transactional
    public ResponseEntity<?> createVehicle(@RequestBody VehiclesDTO dto){
        this.vehiclesService.createVehicle(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{plate}")
    @Transactional
    public ResponseEntity<?> updateVehicle(@PathVariable String plate, @RequestBody VehiclesDTO dto){
        this.vehiclesService.updateVehicle(plate, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{plate}")
    public ResponseEntity<?> deleteVehicle(@PathVariable String plate){
        this.vehiclesService.deleteVehicle(plate);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
