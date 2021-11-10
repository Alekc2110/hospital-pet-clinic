package com.my.project.petclinic.hospital.api.controller;

import com.my.project.petclinic.hospital.api.dto.DoctorDto;
import com.my.project.petclinic.hospital.domain.service.interfaces.DoctorService;
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
public class DoctorController {

    private final DoctorService service;
    private final MapperFacade mapper;

    public DoctorController(DoctorService service, @Qualifier("doctorMapper") MapperFacade mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DoctorDto> getAll() {
       return service.getAllDoctors().stream().map(doctor -> mapper.map(doctor, DoctorDto.class)).collect(Collectors.toList());
    }
}
