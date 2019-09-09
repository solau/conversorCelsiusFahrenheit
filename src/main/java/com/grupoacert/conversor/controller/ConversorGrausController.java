package com.grupoacert.conversor.controller;

import com.grupoacert.conversor.dto.ResultadoDTO;
import com.grupoacert.conversor.dto.GrausDTO;
import com.grupoacert.conversor.service.impl.ConversorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversor/graus")
public class ConversorGrausController {

    @Autowired
    ConversorServiceImpl conversorService;


    @PostMapping
    public ResponseEntity<ResultadoDTO> conversor(@RequestBody GrausDTO grausDTO) {
        return ResponseEntity.ok(conversorService.converter(grausDTO));
    }
}
