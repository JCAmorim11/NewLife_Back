package com.newlife.Newlife.controller;

import com.newlife.Newlife.DTO.ResidentDTO;
import com.newlife.Newlife.service.ResidentService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ResidentService service;
    private final ConversionService conversionService;


    @GetMapping("/{apartment}")
    public ResponseEntity<?> getUserById(@PathVariable("apartment") String apartment){
        List<ResidentDTO> dto = this.residentService.findAllResidents(apartment);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @GetMapping("/all")
    public @ResponseBody
    Page<ResidentDTO> findAllPaginated(
            @RequestParam(required = false) String query,
            Pageable pageable
    ) {
        if(query==null) {
            return this.service.findAll(pageable).map(entity -> this.conversionService.convert(entity, ResidentDTO.class));
        }
        return this.service.findAll(pageable, query).map(entity -> this.conversionService.convert(entity, ResidentDTO.class));
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<?> createResident(@RequestBody ResidentDTO dto){
        this.residentService.createResident(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{apartment}")
    @Transactional
    public ResponseEntity<?> updateResident(@PathVariable String apartment, @RequestBody ResidentDTO dto){
        this.residentService.updateResident(apartment,dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{apartment}")
    public ResponseEntity<?> deleteResident(@PathVariable String cpf){
        this.residentService.deleteResident(cpf);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
