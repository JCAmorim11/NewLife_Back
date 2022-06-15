package com.newlife.Newlife.controller;

import com.newlife.Newlife.DTO.ResidentDTO;
import com.newlife.Newlife.entity.Resident;
import com.newlife.Newlife.service.ResidentService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping("resident")
@AllArgsConstructor
public class ResidentController {
    private final ResidentService residentService;

    @GetMapping("/{apartment}")
    public ResponseEntity<?> getUserById(@PathVariable("apartment") String apartment){
        List<ResidentDTO> dto = this.residentService.findAllResidents(apartment);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<?> createResident(@RequestBody ResidentDTO dto){
        this.residentService.createResident(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{aoartment}")
    @Transactional
    public ResponseEntity<?> updateResident(@PathVariable String apartment, @RequestBody ResidentDTO dto){
        this.residentService.updateResident(apartment,dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{aoartment}")
    public ResponseEntity<?> deleteResident(@PathVariable String cpf){
        this.residentService.deleteResident(cpf);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
