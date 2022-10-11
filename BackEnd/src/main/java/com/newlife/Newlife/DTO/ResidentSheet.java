package com.newlife.Newlife.DTO;

import com.newlife.Newlife.entity.Resident;
import lombok.Builder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.Optional;

@Builder
public class ResidentSheet {

    public String apt;
    public String name;
    public String cpf;
    public String RG;
    public String email;
    public String telephoneA;
    public String telephoneB;
    public String emergencyContact;
    public String emergencyTelephone;
    public String pic;
    public String obs;

    public static ResidentSheet from(Row row){
        return ResidentSheet
                .builder()
                .apt(getField(row,0,"apt"))
                .name(getField(row,1,"name"))
                .cpf(getField(row, 2,"cpf"))
                .RG(getField(row,3, "rg"))
                .email(getField(row,4, "email"))
                .telephoneA(getField(row, 5, "telephoneA"))
                .telephoneB(getField(row, 6, "telephoneB"))
                .emergencyContact(getField(row,7,"emergencyContact"))
                .emergencyTelephone(getField(row, 8, "emergencyTelephone"))
                .pic(getField(row,9,"pic"))
                .obs(getField(row, 10, "obs"))
                .build();

    }

    public static Resident toResident(ResidentSheet sheet){
        return Resident
                .builder()
                .name(sheet.name)
                .email(sheet.email)
                .cpf(sheet.cpf)
                .RG(sheet.RG)
                .telephoneA(sheet.telephoneA)
                .telephoneB(sheet.telephoneB)
                .emergencyContact(sheet.emergencyContact)
                .emergencyTelephone(sheet.emergencyTelephone)
                .build();
    }


    private static String getField(Row row,int cellNumber, String fieldName){
        return Optional
                .ofNullable(row.getCell(cellNumber))
                .map(Cell::getStringCellValue)
                .orElseThrow(()-> new RuntimeException(fieldName + "não pode estar vazio"));
    }
}
