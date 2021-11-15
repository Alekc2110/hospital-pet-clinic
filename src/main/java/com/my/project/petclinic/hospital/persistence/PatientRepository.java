package com.my.project.petclinic.hospital.persistence;

import com.my.project.petclinic.hospital.domain.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {

   List<Patient> findAll();

   Long save(Patient patient);

   Patient update(Patient patient);
}
