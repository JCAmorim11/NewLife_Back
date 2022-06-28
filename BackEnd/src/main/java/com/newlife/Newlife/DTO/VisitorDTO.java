package com.newlife.Newlife.DTO;

import com.newlife.Newlife.entity.Visitor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VisitorDTO {


    private String apartment;
    private String name;
    private String email;
    private String telephoneA;
    private String telephoneB;
    private String RG;
    private String cpf;

    private String obs;
private int id;
    public VisitorDTO(Visitor visitor){
        this.id = 0;
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
