package com.newlife.Newlife.controller;

import com.newlife.Newlife.DTO.ResidentDTO;
import com.newlife.Newlife.service.ResidentService;
import lombok.AllArgsConstructor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.transaction.Transactional;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController
@RequestMapping("resident")
@AllArgsConstructor
public class ResidentController {
    private final ResidentService residentService;
    private final ResidentService service;
    private final ConversionService conversionService;



    @GetMapping("/{apartment}")
    public ResponseEntity<?> getUserById(@PathVariable("apartment") String apartment){
        List<ResidentDTO> dto = this.residentService.findAllResidents(apartment);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @GetMapping("/all")
    public @ResponseBody
    Page<ResidentDTO> findAllPaginated(
            @RequestParam(required = false) String query,
            Pageable pageable
    ) {
        if(query==null) {
            return this.service.findAll(pageable).map(entity -> this.conversionService.convert(entity, ResidentDTO.class));
        }
        else{ return this.service.findAll(pageable, query).map(entity -> this.conversionService.convert(entity, ResidentDTO.class)); }
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<?> createResident(@RequestBody ResidentDTO dto){
        this.residentService.createResident(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importFile(
            @RequestPart(value="file", required = true)
            MultipartFile file) throws IOException, NoSuchAlgorithmException, OpenXML4JException, ParserConfigurationException, SAXException {
        this.residentService.importExcel(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<?> updateResident(@RequestBody ResidentDTO dto){
        this.residentService.updateResident(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResident(@PathVariable("id") long id){
        this.residentService.deleteResident(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
