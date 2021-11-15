package com.my.project.petclinic.hospital.persistence;

import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistence.config.PersistenceLayerBooleanCondition;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class PatientRepositoryStrategy implements PatientRepository {

    private final PatientRepository jdbcPatientRepository;
    private final PatientRepository jpaPatientRepository;
    private final PersistenceLayerBooleanCondition condition;


    public PatientRepositoryStrategy(@Qualifier("jdbcPatientRepository") PatientRepository jdbcPatientRepository,
                                     @Qualifier("jpaPatientRepository") PatientRepository jpaPatientRepository, PersistenceLayerBooleanCondition condition) {
        this.jdbcPatientRepository = jdbcPatientRepository;
        this.jpaPatientRepository = jpaPatientRepository;
        this.condition = condition;
    }

    @Override
    public List<Patient> findAll() {
        if (condition.isJdbc()) {
            return jdbcPatientRepository.findAll();
        } else {
            return jpaPatientRepository.findAll();
        }
    }

    @Override
    public Long save(Patient patient) {
        if (condition.isJdbc()) {
            return jdbcPatientRepository.save(patient);
        } else {
            return jpaPatientRepository.save(patient);
        }
    }

    @Override
    public Patient update(Patient patient) {
        if (condition.isJdbc()) {
            return jdbcPatientRepository.update(patient);
        } else {
            return jpaPatientRepository.update(patient);
        }
    }
}
