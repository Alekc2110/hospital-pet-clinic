package com.my.project.petclinic.hospital.api.controller;

import com.my.project.petclinic.hospital.api.dto.PatientDto;
import com.my.project.petclinic.hospital.domain.service.PatientService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hospital")
public class PatientController {

    private final PatientService service;
    private final MapperFacade mapper;

    public PatientController(PatientService service, @Qualifier("patientMapper") MapperFacade mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PatientDto> getAll() {
        return service.getAllPatients().stream().map(p-> mapper.map(p, PatientDto.class)).collect(Collectors.toList());
    }
}
