package com.my.project.petclinic.hospital.persistance.repository.impl;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.persistance.DoctorRepository;
import com.my.project.petclinic.hospital.persistance.repository.JPADoctorRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("dataJpa")
public class JpaDoctorRepository implements DoctorRepository {

    private final JPADoctorRepository repository;
    private final MapperFacade mapper;

    public JpaDoctorRepository(JPADoctorRepository repository, @Qualifier("doctorJPAMapper") MapperFacade mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Doctor> findAll() {
        return  repository.findAll().stream().map(doctor -> mapper.map(doctor, Doctor.class)).collect(Collectors.toList());
    }
}
