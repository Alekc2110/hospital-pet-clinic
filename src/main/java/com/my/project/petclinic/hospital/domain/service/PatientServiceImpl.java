package com.my.project.petclinic.hospital.domain.service;

import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.domain.service.interfaces.PatientService;
import com.my.project.petclinic.hospital.persistence.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientDao;

    @Override
    public List<Patient> getAllPatients() {
        return patientDao.findAll();
    }
}
