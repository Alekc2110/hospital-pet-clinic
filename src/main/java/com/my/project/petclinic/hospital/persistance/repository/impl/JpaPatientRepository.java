package com.my.project.petclinic.hospital.persistance.repository.impl;

import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistance.PatientRepository;
import com.my.project.petclinic.hospital.persistance.repository.JPAPatientRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("dataJpa")
public class JpaPatientRepository implements PatientRepository {

    private final JPAPatientRepository repository;
    private final MapperFacade mapper;

    public JpaPatientRepository(JPAPatientRepository repository, @Qualifier("patientJPAMapper")MapperFacade mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Patient> findAll() {
        return repository.findAll().stream().map(patient -> mapper.map(patient, Patient.class)).collect(Collectors.toList());
    }
}
