package com.newlife.Newlife.service;

import com.newlife.Newlife.DTO.ResidentDTO;
import com.newlife.Newlife.entity.Resident;
import com.newlife.Newlife.repository.ResidentRepository;
import com.newlife.Newlife.repository.specfications.ResidentSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@Service
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final ResidentRepository repository;


    public List<ResidentDTO> findByApartment(){
        List<Resident> resident = this.residentRepository.findAll();

        if(resident.size() == 0) throw new ResponseStatusException(NOT_FOUND, "Não foram achados moradores neste endereço");
        List<ResidentDTO> listResidents = new ArrayList<>();
        for(int i=0; i < resident.size(); i++){
                listResidents.add(new ResidentDTO(resident.get(i)));
        }
        if(listResidents.size() == 0) throw new ResponseStatusException(NOT_FOUND, "Não foram achados moradores neste endereço");
        return listResidents;

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

    public Page<Resident> findAll(Pageable pageable, String query) {
        return this.repository.findAll(ResidentSpecifications.likeGenericQuery(query), pageable);
    }

    public Page<Resident> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
}
