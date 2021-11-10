package com.my.project.petclinic.hospital.persistence.dao;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.persistence.DoctorRepository;
import com.my.project.petclinic.hospital.persistence.dao.resultSetExtractor.DoctorResultSetExtractor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@Profile("jdbc")
public class JdbcDoctorDao implements DoctorRepository {

    private final JdbcTemplate jdbcTemplate;
    private final DoctorResultSetExtractor rowMapper;

    @Override
    public List<Doctor> findAll() {
        return jdbcTemplate.query(Queries.SELECT_ALL_SQL, rowMapper);
    }
}
