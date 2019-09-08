package com.grupoacert.conversor.controller;

import com.grupoacert.conversor.dto.Resultado;
import com.grupoacert.conversor.form.GrausForm;
import com.grupoacert.conversor.service.ConversorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversor_graus")
public class ConversorGrausController {

    @Autowired
    ConversorService conversorService;


    @GetMapping
    public ResponseEntity<Resultado> conversor(@RequestBody GrausForm grausForm) {
        return ResponseEntity.ok(conversorService.converter(grausForm));
    }
}
