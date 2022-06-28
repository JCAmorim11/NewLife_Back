package com.newlife.Newlife.entity;

import com.newlife.Newlife.DTO.ResidentDTO;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;



@Entity
@Table(name = "TblResident")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resident{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idResident")
    private long id;

    @Column(name = "idLocation")
    private String apartment;
    @Column(name = "dsName")
    private String name;
    @Column(name = "dsEmail")
    private String email;
    @Column(name = "dsCPF")
    private String cpf;
    @Column(name = "dsTelephoneA")
    private String telephoneA;
    @Column(name = "dsTelephoneB")
    private String telephoneB;
    @Column(name = "dsRG")
    private String RG;

    @Column(name = "dsPicture")
    String picture;
    @Column(name = "dsObs")
    String obs;
    @Column(name = "dsEmergencyContact")
    public String emergencyContact;
    @Column(name = "dsEmergencyTelephone")
    public String emergencyTelephone;



    public Resident(ResidentDTO dto) {
        this.id = dto.getId();
        this.apartment = dto.getApartment();
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.telephoneA = dto.getTelephoneA();
        this.telephoneB = dto.getTelephoneB();
        this.RG = dto.getRG();
        this.cpf = dto.getCpf();
        this.obs = dto.getObs();
        this.emergencyContact = dto.getEmergencyContact();
        this.emergencyTelephone = dto.getEmergencyTelephone();
        this.picture = "s";
    }

    public void updateRegistry(@NotNull ResidentDTO dto){
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.telephoneA = dto.getTelephoneA();
        this.telephoneB = dto.getTelephoneB();
        this.RG = dto.getRG();
        this.cpf = dto.getCpf();
        this.obs = dto.getObs();
        this.emergencyContact = dto.getEmergencyContact();
        this.emergencyTelephone = dto.getEmergencyTelephone();
    }


}
