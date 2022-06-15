package com.newlife.Newlife.service;

import com.newlife.Newlife.DTO.VisitorDTO;
import com.newlife.Newlife.entity.Visitor;
import com.newlife.Newlife.repository.VisitorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;

    public VisitorDTO findByApartment(String apartment){
        Visitor visit = this.visitorRepository.findByApartment(apartment).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new VisitorDTO(visit);
    }

    public void createVisitor(VisitorDTO dto, String apartment){
        Visitor neoVisit = new Visitor((dto));
        this.visitorRepository.save(neoVisit);
    }

    public void updateVisitor(String apartment, VisitorDTO dto){
        Visitor visit = this.visitorRepository.findByApartment(apartment).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        visit.updateRegistry(dto);
        visitorRepository.save(visit);
    }
    public void deleteVisitor(String apartment){
        Visitor visit = this.visitorRepository.findByApartment(apartment).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        visitorRepository.delete(visit);
    }
}
