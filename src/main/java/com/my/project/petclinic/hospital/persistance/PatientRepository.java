package com.my.project.petclinic.hospital.persistance;

import com.my.project.petclinic.hospital.domain.model.Patient;

import java.util.List;

public interface PatientRepository {

    List<Patient> findAll();
}
