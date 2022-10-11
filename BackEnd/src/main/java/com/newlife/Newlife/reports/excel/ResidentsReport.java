package com.newlife.Newlife.reports.excel;


import com.newlife.Newlife.entity.Resident;
import com.newlife.Newlife.repository.ResidentRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResidentsReport {

    public static ByteArrayOutputStream generateResidentList(List<ReportData> residentos, String document) throws Exception {
        BaseExcelReport base = new BaseExcelReport(
                "Lista de Moradores",
                "",
                document
        );
       Sheet sheet = base.getWorkbook().getSheetAt(0);
       int rowIndex = BaseExcelReport.FIRST_AVAILABLE_ROW;
       Row row = sheet.createRow(rowIndex++);

       base.createHeaderCell(0,row).setCellValue("Cod.");
        base.createHeaderCell(1,row).setCellValue("Apartamento");
        base.createHeaderCell(2,row).setCellValue("Nome");
        base.createHeaderCell(3,row).setCellValue("CPF");
        base.createHeaderCell(4,row).setCellValue("Email");
        base.createHeaderCell(5,row).setCellValue("RG");
        base.createHeaderCell(6,row).setCellValue("Telefone 1");
        base.createHeaderCell(7,row).setCellValue("Telefone 2");

        sheet.createRow(rowIndex++);

        Map<String, List<ReportData>> map = mountMap(residentos);

        for(String s: map.keySet()){
           int firstRow = rowIndex;

           for(ReportData d: map.get(s)){
               Row dataRow = sheet.createRow(rowIndex++);
               base.createIdCell(0, dataRow).setCellValue(d.getId());
               base.createNormalCell(1, dataRow).setCellValue(d.getApartment());
               base.createNormalCell(2, dataRow).setCellValue(d.getName());
               base.createNormalCell(3, dataRow).setCellValue(d.getCPF());
               base.createNormalCell(4, dataRow).setCellValue(d.getEmail());
               base.createNormalCell(5, dataRow).setCellValue(d.getRG());
               base.createNormalCell(6, dataRow).setCellValue(d.getTelephoneA());
               base.createNormalCell(7, dataRow).setCellValue(d.getTelephoneB());
           }

           sheet.createRow(rowIndex++);

           if(map.get(s).size() > 1){
               sheet.addMergedRegion(new CellRangeAddress(firstRow, rowIndex - 2, 0, 0));
               sheet.addMergedRegion(new CellRangeAddress(firstRow, rowIndex - 2, 1, 1));
               sheet.addMergedRegion(new CellRangeAddress(firstRow, rowIndex - 2, 2, 2));
           }
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        base.getWorkbook().write(stream);

        return stream;
    }


    private static Map<String, List<ReportData>> mountMap(List<ReportData> moradores) {
        Map<String, List<ReportData>> map = new HashMap<>();

        moradores.forEach(c -> {
            if(map.get(c.getName()) == null) map.put(c.getName(), new ArrayList<>());

            map.get(c.getName()).add(c);
        });

        return map;
    }

   public static List<ReportData> generateData(List<Resident> residents, ResidentRepository residentRepository){
        List<ReportData> data = new ArrayList<>();

        residents.forEach(resident ->{
            data.add(
                    ReportData.builder()
                      .id(resident.getId())
                      .name(resident.getName())
                      .apartment(resident.getApartment())
                      .CPF(resident.getCpf())
                      .RG(resident.getRG())
                      .email(resident.getEmail())
                      .telephoneA(resident.getTelephoneA())
                      .telephoneB(resident.getTelephoneB())
                      .build()
            );
        });
        return data;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReportData {
        private Long id;
        private String name;
        private String apartment;
        private String email;
        private String CPF;
        private String RG;
        private String telephoneA;
        private String telephoneB;

    }
}
