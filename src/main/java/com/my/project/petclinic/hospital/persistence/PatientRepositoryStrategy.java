package com.my.project.petclinic.hospital.persistence;

import com.my.project.petclinic.hospital.domain.model.Patient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class PatientRepositoryStrategy implements PatientRepository {

    private final PatientRepository jdbcPatientRepository;
    private final PatientRepository jpaPatientRepository;

    public PatientRepositoryStrategy(@Qualifier("jdbcPatientRepository") PatientRepository jdbcPatientRepository,
                                     @Qualifier("jpaPatientRepository") PatientRepository jpaPatientRepository) {
        this.jdbcPatientRepository = jdbcPatientRepository;
        this.jpaPatientRepository = jpaPatientRepository;
    }

    @Override
    public List<Patient> findAll() {
        boolean isJdbc = true;
        if (isJdbc) {
            return jdbcPatientRepository.findAll();
        } else {
            return jpaPatientRepository.findAll();
        }
    }
}
