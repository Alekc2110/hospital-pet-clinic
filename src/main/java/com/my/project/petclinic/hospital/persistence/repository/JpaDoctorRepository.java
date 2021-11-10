package com.my.project.petclinic.hospital.persistence.repository;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.persistence.DoctorRepository;
import com.my.project.petclinic.hospital.persistence.repository.interfaces.JpaDoctorRepo;
import com.my.project.petclinic.hospital.persistence.repository.mapper.MapStructDoctorMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
@Profile("dataJpa")
public class JpaDoctorRepository implements DoctorRepository {

    private final JpaDoctorRepo repository;
    private final MapStructDoctorMapper mapStructDoctorMapper;

    @Override
    public List<Doctor> findAll() {
        return repository.findAll().stream().map(mapStructDoctorMapper::entityToModel).collect(Collectors.toList());
    }
}
