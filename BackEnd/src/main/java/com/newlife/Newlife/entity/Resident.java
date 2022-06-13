package com.newlife.Newlife.entity;

public class Resident extends Person{

    public String emergencyContact;
    public String emergencyTelephone;

    public Resident(String apartment, String name, String email, String telephoneA, String telephoneB, String RG, String cpf, String picture, String obs) {
        super(apartment, name, email, telephoneA, telephoneB, RG, cpf, picture, obs);
        this.emergencyContact = this.emergencyContact;
        this.emergencyTelephone = this.emergencyTelephone;
    }
}
