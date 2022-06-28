package com.newlife.Newlife.controller;

import com.newlife.Newlife.DTO.VisitorDTO;
import com.newlife.Newlife.service.VisitorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("visitor")
@AllArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;
    @GetMapping("/{apartment}")
    public ResponseEntity<?> getUserByID(@PathVariable String apartment){
        VisitorDTO dto = this.visitorService.findByApartment(apartment);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<?> createVisitor(@RequestBody VisitorDTO dto){
        this.visitorService.updateVisitor(dto.getApartment(),dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> updateVisitor(@RequestBody VisitorDTO dto){
        this.visitorService.updateVisitor(dto.getApartment(), dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteVisitor(@RequestBody VisitorDTO dto){
        this.visitorService.deleteVisitor(dto.getApartment());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
