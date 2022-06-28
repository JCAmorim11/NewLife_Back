package com.newlife.Newlife.controller;

import com.newlife.Newlife.DTO.WorkerDTO;
import com.newlife.Newlife.service.WorkerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("worker")
@AllArgsConstructor
public class WorkerController {

    private final WorkerService workerService;
    @GetMapping("{apartment}")
    public ResponseEntity<?> getUserByID(@PathVariable String apartment){
        WorkerDTO dto = this.workerService.findByApartment(apartment);
        return  ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    @PostMapping()
    @Transactional
    public ResponseEntity<?> createWorker(@RequestBody WorkerDTO dto){
        this.workerService.createWorker(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> updateWorker(@PathVariable String apartment, @RequestBody WorkerDTO dto){
        this.workerService.updateWorker(apartment, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteWorker(@PathVariable String apartment){
       this.workerService.deleteWorker(apartment);
       return new ResponseEntity<>(HttpStatus.OK);
    }


}
