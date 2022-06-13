package com.newlife.Newlife.controller;

import com.newlife.Newlife.service.ResidentService;
import lombok.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("morador")
@AllArgsConstructor
public class ResidentController {
    private final ResidentService residentService;

    /*    @GetMapping()
    @PostMapping()
    @PutMapping()
    @DeleteMapping()*/
}
