package com.newlife.Newlife.controller;

import com.newlife.Newlife.DTO.VehiclesDTO;
import com.newlife.Newlife.service.VehiclesService;
import lombok.AllArgsConstructor;
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
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> updateVehicle(@RequestBody VehiclesDTO dto){
        this.vehiclesService.updateVehicle(dto.getPlate(), dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteVehicle(@RequestBody VehiclesDTO dto){
        this.vehiclesService.deleteVehicle(dto.getPlate());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
