package com.newlife.Newlife.service;

import com.newlife.Newlife.DTO.ResidentDTO;
import com.newlife.Newlife.DTO.ResidentSheet;
import com.newlife.Newlife.entity.Resident;
import com.newlife.Newlife.repository.ResidentRepository;
import com.newlife.Newlife.repository.specfications.ResidentSpecifications;
import lombok.AllArgsConstructor;
import org.apache.commons.collections.IteratorUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@Service
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final ResidentRepository repository;
    private final FileService fileService;

    public List<ResidentDTO> findByApartment(String apartment){
        List<Resident> resident = this.residentRepository.findAll();

        if(resident.size() == 0) throw new ResponseStatusException(NOT_FOUND, "Não foram achados moradores neste endereço");
        List<ResidentDTO> listResidents = new ArrayList<>();
        for(int i=0; i < resident.size(); i++){
                listResidents.add(new ResidentDTO(resident.get(i)));
        }
        if(listResidents.size() == 0) throw new ResponseStatusException(NOT_FOUND, "Não foram achados moradores neste endereço");
        return listResidents;

    }

    public List<ResidentDTO> findAllResidents(String apartment){
        List<Resident> residents = this.residentRepository.findAll();
        if(residents.size() == 0) throw new ResponseStatusException(NOT_FOUND, "Não foram achados moradores neste endereço");
        List<ResidentDTO> listResidents = new ArrayList<>();
        for(int i=0; i < residents.size(); i++){
            if(Objects.equals(residents.get(i).getApartment(), apartment)) {
                listResidents.add(new ResidentDTO(residents.get(i)));
            }
        }
        if(listResidents.size() == 0) throw new ResponseStatusException(NOT_FOUND, "Não foram achados moradores neste endereço");
        return listResidents;
    }

    public void createResident(ResidentDTO dto){
        Resident neoResident = new Resident(dto);
        this.residentRepository.save(neoResident);
    }

    public void updateResident(ResidentDTO dto){
        List<Resident> resident = this.residentRepository.findAllByApartment(dto.getApartment());
        if(resident.size() == 0) throw new ResponseStatusException(NOT_FOUND, "Não foram achados moradores neste endereço");
        String resCpf = resident.get(0).getCpf();
        if(resident.size() < 2){
            resident.get(0).updateRegistry(dto);
            residentRepository.save(resident.get(0));
            return;
        }else{
            String dtoCpf = dto.getCpf();
            for(int i=1; i < resident.size(); i++){
                if(Objects.equals(resCpf, dtoCpf)){
                    resident.get(i).updateRegistry(dto);
                    residentRepository.save(resident.get(i));
                    return;
                }else {
                    resCpf = resident.get(i).getCpf();
                }
            }
        }
        throw new ResponseStatusException(NOT_FOUND, "Não foi achado morador com este cpf");
    }

    public void deleteResident(long id){
        Resident resident = this.residentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "NÃO ENCONTRADO ID"));;
        this.residentRepository.delete(resident);
    }


    public void importExcel(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        
  //      Path filePath = this.fileService.save(file,"data");
       // FileInputStream inputFile = new FileInputStream(file.);
        InputStream inputStream =  new BufferedInputStream(file.getInputStream());

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<Row> rows = this.toList(sheet.iterator());
        rows.remove(0);
        System.out.println(rows);
        Map<Resident, List<ResidentSheet>> detailToEnd = rows
                .stream()
                .map(ResidentSheet::from)
                .collect(Collectors.groupingBy(ResidentSheet::toResident));
   /*     List<Resident> resident = new ArrayList<>();
        int i=0;
        for (List<ResidentSheet> residentSheets : detailToEnd.values()) {

            resident.add(residentSheets.indexOf(i));
            for(ResidentSheet model : residentSheets) {
                System.out.println(model.apt.toUpperCase());
            }
            i++;
        }*/

       // this.residentRepository.saveAll();
        System.out.println();
        return;
    }

    public <T> List toList(Iterator<T> iterator){
        return IteratorUtils.toList(iterator);
    }







    public Page<Resident> findAll(Pageable pageable, String query) {
        return this.repository.findAll(ResidentSpecifications.likeGenericQuery(query), pageable);
    }

    public Page<Resident> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
}
