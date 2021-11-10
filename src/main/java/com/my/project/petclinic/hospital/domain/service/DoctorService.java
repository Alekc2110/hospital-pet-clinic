package com.my.project.petclinic.hospital.domain.service;

import com.my.project.petclinic.hospital.domain.model.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
}
