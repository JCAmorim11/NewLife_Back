package com.newlife.Newlife.service;

import com.newlife.Newlife.DTO.WorkerDTO;
import com.newlife.Newlife.entity.Visitor;
import com.newlife.Newlife.entity.Worker;
import com.newlife.Newlife.repository.WorkerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
@Service
public class WorkerService {

    private final WorkerRepository workerRepository;

    public WorkerDTO findByApartment(String apartment){
        Worker worker = this.workerRepository.findByApartment(apartment).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new WorkerDTO(worker);
    }

    public void createWorker(WorkerDTO dto){
        Optional<Worker> workerConflict = this.workerRepository.findByApartment(dto.getApartment());
        if(workerConflict.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Já existe um trabalhador com esse cpf cadastrado no BD");
    }

    public void updateWorker(String apartment, WorkerDTO dto){
        Worker worker = this.workerRepository.findByApartment(apartment).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        worker.updateRegistry(dto);
        workerRepository.save(worker);
    }

    public void deleteWorker(String apartment){
        Worker worker = this.workerRepository.findByApartment(apartment).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        workerRepository.delete(worker);

    }
}
