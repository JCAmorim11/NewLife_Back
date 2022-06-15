package com.newlife.Newlife.DTO;

import com.newlife.Newlife.entity.Resident;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidentDTO {
    private String apartment;
    private String name;
    private String email;
    private String telephoneA;
    private String telephoneB;
    private String RG;
    private String cpf;

    private String obs;
    private String emergencyContact;
    private String emergencyTelephone;

    public ResidentDTO(Resident resident){
        this.apartment = resident.getApartment();
        this.name = resident.getName();
        this.email = resident.getEmail();
        this.telephoneA = resident.getTelephoneA();
        this.telephoneB = resident.getTelephoneB();
        this.RG = resident.getRG();
        this.cpf = resident.getCpf();
        this.emergencyContact = resident.getEmergencyContact();
        this.emergencyTelephone = resident.getEmergencyTelephone();
        this.obs = resident.getObs();
    }
}
