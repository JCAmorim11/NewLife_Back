package com.newlife.Newlife.DTO;

import com.newlife.Newlife.entity.Resident;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResidentDTO {
    private int id =0;
    private String apartment;
    private String name;
    private String email;
    private String telephoneA;
    private String telephoneB;
    private String RG;
    private String cpf;

    private String picture;

    private String obs;
    private String emergencyContact;
    private String emergencyTelephone;

    public ResidentDTO(Resident resident){
        this.id = 0;
        this.apartment = resident.getApartment();
        this.name = resident.getName();
        this.email = resident.getEmail();
        this.telephoneA = resident.getTelephoneA();
        this.telephoneB = resident.getTelephoneB();
        this.RG = resident.getRG();
        this.cpf = resident.getCpf();
        this.picture = "s";
        this.emergencyContact = resident.getEmergencyContact();
        this.emergencyTelephone = resident.getEmergencyTelephone();
        this.obs = resident.getObs();
    }
}
