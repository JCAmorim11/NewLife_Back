package com.newlife.Newlife.DTO;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Builder
public class ResidentInfoSheet {
    public String name;
    public String email;
    public String telephoneA;
    public String telephoneB;
    public String RG;
    public String cpf;
}
