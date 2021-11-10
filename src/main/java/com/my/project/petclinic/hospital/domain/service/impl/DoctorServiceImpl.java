package com.my.project.petclinic.hospital.domain.service.impl;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.domain.service.DoctorService;
import com.my.project.petclinic.hospital.persistance.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorDao;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDao.findAll();
    }
}
