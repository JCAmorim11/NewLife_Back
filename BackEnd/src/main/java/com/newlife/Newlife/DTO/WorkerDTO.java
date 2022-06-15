package com.newlife.Newlife.DTO;

import com.newlife.Newlife.entity.Worker;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerDTO {

    private String apartment;
    private String name;
    private String email;
    private String telephoneA;
    private String telephoneB;
    private String RG;
    private String cpf;

    private String obs;

    public WorkerDTO(Worker worker){
        this.apartment = worker.getApartment();
        this.name = worker.getName();
        this.email = worker.getEmail();
        this.telephoneA = worker.getTelephoneA();
        this.telephoneB = worker.getTelephoneB();
        this.RG = worker.getRG();
        this.cpf = worker.getCpf();
        this.obs = worker.getObs();
    }
}
