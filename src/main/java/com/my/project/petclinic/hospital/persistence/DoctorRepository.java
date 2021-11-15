package com.my.project.petclinic.hospital.persistence;

import com.my.project.petclinic.hospital.domain.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {

    List<Doctor> findAll();

    Long save(Doctor doctor);

    Doctor update(Doctor doctor);
}
