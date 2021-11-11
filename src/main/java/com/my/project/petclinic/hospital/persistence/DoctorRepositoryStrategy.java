package com.my.project.petclinic.hospital.persistence;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class DoctorRepositoryStrategy implements DoctorRepository {

    private final DoctorRepository jdbcDoctorRepository;
    private final DoctorRepository jpaDoctorRepository;

    public DoctorRepositoryStrategy(@Qualifier("jpaDoctorRepository") DoctorRepository jpaDoctorRepository,
                                    @Qualifier("jdbcDoctorRepository") DoctorRepository jdbcDoctorRepository) {
        this.jpaDoctorRepository = jpaDoctorRepository;
        this.jdbcDoctorRepository = jdbcDoctorRepository;
    }

    @Override
    public List<Doctor> findAll() {
        boolean isJdbc = true; //TODO: find a way how to init this variable
        if (isJdbc) {
            return jdbcDoctorRepository.findAll();
        } else {
            return jpaDoctorRepository.findAll();
        }
    }
}
