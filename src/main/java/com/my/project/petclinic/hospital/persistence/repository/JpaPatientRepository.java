package com.my.project.petclinic.hospital.persistence.repository;

import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistence.PatientRepository;
import com.my.project.petclinic.hospital.persistence.repository.interfaces.JpaPatientRepo;
import com.my.project.petclinic.hospital.persistence.repository.mapper.MapStructPatientMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
@Profile("dataJpa")
public class JpaPatientRepository implements PatientRepository {

    private final JpaPatientRepo repository;
    private final MapStructPatientMapper mapStructPatientMapper;

    @Override
    public List<Patient> findAll() {
        return repository.findAll().stream().map(mapStructPatientMapper::entityToModel).collect(Collectors.toList());
    }
}
