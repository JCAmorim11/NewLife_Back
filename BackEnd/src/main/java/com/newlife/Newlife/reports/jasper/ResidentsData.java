package com.newlife.Newlife.reports.jasper;

import com.newlife.Newlife.entity.Resident;
import com.newlife.Newlife.repository.ResidentRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResidentsData {

    public JasperPrint generateResidentList(List<Resident> residents, ResidentRepository residentRepository, String fileName, String extension) throws JRException, IOException, JSONException {

        List<ResidentsJasper> residentsJaspers = new ArrayList<>();

        residents.forEach(resident -> {
            System.out.println(resident.getApartment());
            residentsJaspers.add(
                    ResidentsJasper.builder()
                            .id(resident.getId())
                            .apartment(resident.getApartment())
                            .name(resident.getName())
                            .email(resident.getEmail())
                            .CPF(resident.getCpf())
                            .RG(resident.getRG())
                            .telephoneA(resident.getTelephoneA())
                            .telephoneB(resident.getTelephoneB())
                            .build()
            );
        });
        Map<String, Object> mapAsJSON = new HashMap<>();
        InputStream inputStreamAsJSON = new ByteArrayInputStream(this.toJSON(residentsJaspers));
        mapAsJSON.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, inputStreamAsJSON);

        InputStream inputStreamAsJasperListMasterFile;
        System.out.println(extension);
        if(extension.equals("pdf")){
            inputStreamAsJasperListMasterFile = this.getClass().getResourceAsStream("/reports/apartamentLegais.jasper");
        }else{
            inputStreamAsJasperListMasterFile = this.getClass().getResourceAsStream("/reports/Apartamentos-xls.jasper");
        }


        InputStream inputStreamAsJasperFileData = this.getClass().getResourceAsStream("/reports/apartamentos-data.jasper");
        net.sf.jasperreports.engine.JasperReport subReportApartmentData = (net.sf.jasperreports.engine.JasperReport) JRLoader.loadObject(inputStreamAsJasperFileData);
        mapAsJSON.put("subReportApartmentData", subReportApartmentData);

        mapAsJSON.put("fileName", fileName);

        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStreamAsJasperListMasterFile, mapAsJSON);

        return jasperPrint;
    }



    private byte[] toJSON(List<ResidentsJasper> jasperReport) throws JSONException {
        JSONObject fullReport = new JSONObject();
        JSONObject report = new JSONObject();

        JSONArray data = new JSONArray();
        for(ResidentsJasper resident : jasperReport){
            JSONObject jsonObject = new JSONObject(resident);
            JSONObject residentJson = new JSONObject();
            residentJson.put("residentData", jsonObject);

            data.put(residentJson);
        }
        /*Appending each JSONObject or JSONArray to the report*/

        report.put("data", data);
        fullReport.put("report", report);
        String prettyJson = fullReport.toString(4);
        System.out.println(prettyJson);

        return fullReport.toString().getBytes();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResidentsJasper {
        private Long id;
        private String apartment;
        private String name;
        private String email;
        private String CPF;
        private String RG;
        private String telephoneA;
        private String telephoneB;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResidentsJasperAsList {
        List<ResidentsJasper> residentsJas;
    }
}
