package com.my.project.petclinic.hospital.persistance.repository.interfaces;

import com.my.project.petclinic.hospital.persistance.entity.DoctorEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaDoctorRepo extends JpaRepository<DoctorEntity, Long> {
    @Override
    @EntityGraph(attributePaths = {"patientList"})
    List<DoctorEntity> findAll();
}

