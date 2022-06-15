package com.newlife.Newlife.service;

import com.newlife.Newlife.DTO.VehiclesDTO;
import com.newlife.Newlife.entity.Vehicles;
import com.newlife.Newlife.repository.VehiclesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
@Service
public class VehiclesService {

    private final VehiclesRepository vehiclesRepository;

    public VehiclesDTO findByPlate(String plate){
        Vehicles vehicles = this.vehiclesRepository.findByPlate(plate).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "nÃO ENCONTRADO"));
        return new VehiclesDTO(vehicles);
    }

    public void createVehicle(VehiclesDTO dto){
        //inserir codigo
        Optional<Vehicles>  vehiclesConflit = this.vehiclesRepository.findByPlate(dto.getPlate());
        if(vehiclesConflit.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Já existe um carro com essa placa cadastrada no BD");
        Vehicles neoVehicles = new Vehicles(dto);
        this.vehiclesRepository.save(neoVehicles);
    }

    public void updateVehicle(String plate, VehiclesDTO dto){
        Vehicles vehicles = this.vehiclesRepository.findByPlate(plate).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        vehicles.updateRegistry(dto);
        vehiclesRepository.save(vehicles);
    }

    public void deleteVehicle(String plate){
       Vehicles vehicles = this.vehiclesRepository.findByPlate(plate).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
       vehiclesRepository.delete(vehicles);
    }
}
