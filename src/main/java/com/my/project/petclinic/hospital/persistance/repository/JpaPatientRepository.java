package com.my.project.petclinic.hospital.persistance.repository;

import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistance.PatientRepository;
import com.my.project.petclinic.hospital.persistance.repository.interfaces.JpaPatientRepo;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("dataJpa")
public class JpaPatientRepository implements PatientRepository {

    private final JpaPatientRepo repository;
    private final MapperFacade mapper;

    public JpaPatientRepository(JpaPatientRepo repository, @Qualifier("patientJPAMapper")MapperFacade mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Patient> findAll() {
        return repository.findAll().stream().map(patient -> mapper.map(patient, Patient.class)).collect(Collectors.toList());
    }
}
