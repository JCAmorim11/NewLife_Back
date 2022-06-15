package com.newlife.Newlife.DTO;

import com.newlife.Newlife.entity.Visitor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitorDTO {


    private String apartment;
    private String name;
    private String email;
    private String telephoneA;
    private String telephoneB;
    private String RG;
    private String cpf;

    private String obs;

    public VisitorDTO(Visitor visitor){
        this.apartment = visitor.getApartment();
        this.name = visitor.getName();
        this.email = visitor.getEmail();
        this.telephoneA = visitor.getTelephoneA();
        this.telephoneB = visitor.getTelephoneB();
        this.RG = visitor.getRG();
        this.cpf = visitor.getCpf();
        this.obs = visitor.getObs();
    }

}
