package com.my.project.petclinic.hospital.persistance.repository;

import com.my.project.petclinic.hospital.persistance.entity.PatientEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JPAPatientRepository extends JpaRepository<PatientEntity, Long> {

    @Override
    @EntityGraph(attributePaths = {"doctorList"})
    List<PatientEntity> findAll();
}
