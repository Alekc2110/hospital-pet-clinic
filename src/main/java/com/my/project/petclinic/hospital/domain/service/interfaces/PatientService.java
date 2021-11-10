package com.my.project.petclinic.hospital.domain.service.interfaces;

import com.my.project.petclinic.hospital.domain.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
}
