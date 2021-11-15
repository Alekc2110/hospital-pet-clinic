package com.my.project.petclinic.hospital.persistence;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.persistence.config.PersistenceLayerBooleanCondition;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class DoctorRepositoryStrategy implements DoctorRepository {

    private final DoctorRepository jdbcDoctorRepository;
    private final DoctorRepository jpaDoctorRepository;
    private final PersistenceLayerBooleanCondition condition;

    public DoctorRepositoryStrategy(@Qualifier("jpaDoctorRepository") DoctorRepository jpaDoctorRepository,
                                    @Qualifier("jdbcDoctorRepository") DoctorRepository jdbcDoctorRepository, PersistenceLayerBooleanCondition condition) {
        this.jpaDoctorRepository = jpaDoctorRepository;
        this.jdbcDoctorRepository = jdbcDoctorRepository;
        this.condition = condition;
    }

    @Override
    public List<Doctor> findAll() {
        if (condition.isJdbc()) {
            return jdbcDoctorRepository.findAll();
        } else {
            return jpaDoctorRepository.findAll();
        }
    }

    @Override
    public Long save(Doctor doctor) {
        if (condition.isJdbc()) {
            return jdbcDoctorRepository.save(doctor);
        } else {
            return jpaDoctorRepository.save(doctor);
        }
    }

    @Override
    public Doctor update(Doctor doctor) {
        if (condition.isJdbc()) {
            return jdbcDoctorRepository.update(doctor);
        } else {
            return jpaDoctorRepository.update(doctor);
        }
    }
}
