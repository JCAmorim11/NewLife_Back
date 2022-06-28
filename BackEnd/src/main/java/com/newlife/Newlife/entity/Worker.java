package com.newlife.Newlife.entity;

import com.newlife.Newlife.DTO.WorkerDTO;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "tblWorker")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "idLocation")
    private String apartment;
    @Column(name = "dsName")
    private String name;
    @Column(name = "dsEmail")
    private String email;
    @Column(name = "dsTelephoneA")
    private String telephoneA;
    @Column(name = "dsTelephoneB")
    private String telephoneB;
    @Column(name = "dsRG")
    private String RG;
    @Column(name = "dsCPF")
    private String cpf;
    @Column(name = "dsObs")
    private String obs;

    @Column(name = "dsPicture")
    String picture;


   public Worker(WorkerDTO dto) {
       this.id = dto.getId();
        this.apartment = dto.getApartment();
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.telephoneA = dto.getTelephoneA();
        this.telephoneB = dto.getTelephoneB();
        this.RG = dto.getRG();
        this.cpf = dto.getCpf();
        this.picture = "s";
        this.obs = dto.getObs();
    }

    public void updateRegistry(WorkerDTO dto){
        this.apartment = dto.getApartment();
        this.name = dto.getName();
        this.email = dto.getTelephoneA();
        this.telephoneA = dto.getTelephoneA();
        this.telephoneB = dto.getTelephoneB();
        this.RG = dto.getRG();
        this.cpf = dto.getCpf();
        this.obs = dto.getObs();
    }
}
