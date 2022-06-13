package com.newlife.Newlife.entity;

public abstract class Person {
    public String apartment;
    public String name;
    public String email;
    public String telephoneA;
    public String telephoneB;
    public String RG;
    public String cpf;
    public String picture;
    public String obs;

    public Person(String apartment, String name, String email, String telephoneA, String telephoneB, String RG, String cpf, String picture, String obs) {
        this.apartment = apartment;
        this.name = name;
        this.email = email;
        this.telephoneA = telephoneA;
        this.telephoneB = telephoneB;
        this.RG = RG;
        this.cpf = cpf;
        this.picture = picture;
        this.obs = obs;
    }



}
