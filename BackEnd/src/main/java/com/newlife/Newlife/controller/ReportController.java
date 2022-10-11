package com.newlife.Newlife.controller;


import com.newlife.Newlife.entity.Resident;
import com.newlife.Newlife.reports.jasper.CustomReportDataSource;
import com.newlife.Newlife.reports.jasper.ResidentsData;
import com.newlife.Newlife.repository.ResidentRepository;
import com.newlife.Newlife.service.ResidentService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("reports")
@AllArgsConstructor
public class ReportController {

    private final ResidentService residentService;
    private final ResidentsData residentsData;
    private final ResidentRepository residentRepository;

    private void generateReportJasper(String report, List objects, String fileName, HttpServletResponse response, String apartment) throws Exception {
        InputStream jasperStream = this.getClass().getResourceAsStream(report);

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

        Map<String, Object> params = new HashMap<>();
        params.put("fileName", fileName);
        if(apartment != null){
            params.put("apartamento: ", apartment);
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, (Connection) new CustomReportDataSource(objects));

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "inline; filename="+fileName+".pdf");
        final OutputStream outStream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }


    private ResponseEntity<ByteArrayResource> generateExcel(
        String document, ByteArrayOutputStream out) throws Exception {
        byte[] buf = out.toByteArray();

        out.close();

        ByteArrayResource bas = new ByteArrayResource(buf);


        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + document + ".xls")
                .contentType(mediaType)
                .body(bas);
    }

    private ResponseEntity<ByteArrayResource> exportJasper(String extension, HttpServletResponse response, JasperPrint jasperPrint, String fileName) throws Exception {
      if(extension.equals("pdf")) {
          response.setContentType("application/octet-stream");

          response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
          final OutputStream outputStream = response.getOutputStream();
          JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
      }else {
          Exporter exporter = new JRXlsExporter();
          ByteArrayOutputStream out = new ByteArrayOutputStream();
          exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
          exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
          exporter.exportReport();
          return generateExcel(fileName, out);
      }
      return null;
    }


    @GetMapping("/residentList/{extension}/{fileName}")
    public ResponseEntity<ByteArrayResource> getResidentList(HttpServletResponse response,
            @PathVariable String extension,
            @PathVariable String fileName) throws Exception, IOException, JRException {
       List<Resident> residents;
       residents = residentRepository.findAll();
       JasperPrint jasperPrint = residentsData.generateResidentList(residents, residentRepository, fileName, extension);
       return exportJasper(extension, response, jasperPrint, fileName);
   //     return data;
    }
}
