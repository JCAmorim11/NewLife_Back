package com.newlife.Newlife.controller;

import com.newlife.Newlife.service.VehiclesService;
import lombok.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("veiculo")
@AllArgsConstructor
public class VehiclesController {

    private final VehiclesService veiclesService;

/*    @GetMapping()
    @PostMapping()
    @PutMapping()
    @DeleteMapping()*/

}
