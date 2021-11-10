package com.my.project.petclinic.hospital.persistence;

import com.my.project.petclinic.hospital.domain.model.Doctor;

import java.util.List;

public interface DoctorRepository {

    List<Doctor> findAll();
}
