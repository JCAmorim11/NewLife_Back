package com.newlife.Newlife.entity;

import com.newlife.Newlife.DTO.VisitorDTO;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "tblVisitor")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVisitor")
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

    public Visitor(VisitorDTO dto) {
        this.apartment = dto.getApartment();
        this.name = dto.getName();
        this.email = dto.getTelephoneA();
        this.telephoneA = dto.getTelephoneA();
        this.telephoneB = dto.getTelephoneB();
        this.RG = dto.getRG();
        this.cpf = dto.getCpf();
        this.obs = dto.getObs();
    }

    public void updateRegistry(VisitorDTO dto){
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
