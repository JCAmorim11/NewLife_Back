package com.newlife.Newlife.service;

import com.newlife.Newlife.DTO.ResidentDTO;
import com.newlife.Newlife.entity.Resident;
import com.newlife.Newlife.repository.ResidentRepository;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Where;
import org.hibernate.hql.internal.classic.WhereParser;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@Service
public class ResidentService {

    private final ResidentRepository residentRepository;

    public ResidentDTO findByApartment(String apartment){
        Resident resident = this.residentRepository.findByApartment(apartment).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResidentDTO(resident);
    }

    public List<ResidentDTO> findAllResidents(String apartment){
        List<Resident> residents = this.residentRepository.findAll();
        if(residents.size() == 0) throw new ResponseStatusException(NOT_FOUND, "Não foram achados moradores neste endereço");
        List<ResidentDTO> listResidents = new ArrayList<>();
        for(int i=0; i < residents.size(); i++){
            if(Objects.equals(residents.get(i).getApartment(), apartment)) {
                listResidents.add(new ResidentDTO(residents.get(i)));
            }
        }
        if(listResidents.size() == 0) throw new ResponseStatusException(NOT_FOUND, "Não foram achados moradores neste endereço");
        return listResidents;
    }

    public void createResident(ResidentDTO dto){
        Resident neoResident = new Resident(dto);
        this.residentRepository.save(neoResident);
    }

    public void updateResident(String apartment, ResidentDTO dto){
        Resident resident = this.residentRepository.findByApartment(apartment).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        resident.updateRegistry(dto);
        residentRepository.save(resident);
    }

    public void deleteResident(String apartment){
        Resident resident = this.residentRepository.findByApartment(apartment).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        residentRepository.delete(resident);
    }
}
