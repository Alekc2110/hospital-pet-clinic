package com.my.project.petclinic.hospital.persistence.repository;

import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistence.PatientRepository;
import com.my.project.petclinic.hospital.persistence.entity.PatientEntity;
import com.my.project.petclinic.hospital.persistence.repository.interfaces.JpaPatientRepo;
import com.my.project.petclinic.hospital.persistence.repository.mapper.MapStructPatientMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository(value = "jpaPatientRepository")
 class JpaPatientRepository implements PatientRepository {

    private final JpaPatientRepo repository;
    private final MapStructPatientMapper mapStructPatientMapper;


    @Override
    public List<Patient> findAll() {
        return repository.findAll().stream().map(mapStructPatientMapper::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Long save(Patient patient) {
        return repository.save(mapStructPatientMapper.modelToEntity(patient)).getId();
    }

    @Override
    public Patient update(Patient patient) {
        final PatientEntity patientEntity = repository.findById(patient.getId()).
                orElseThrow(() -> new EntityNotFoundException(String.format("patient with id %s not found", patient.getId())));

        patientEntity.setAge(patient.getAge());
        patientEntity.setName(patient.getName());
        patientEntity.setSurName(patient.getSurName());
        repository.flush();

        return mapStructPatientMapper.entityToModel(patientEntity);
    }
}
