package com.my.project.petclinic.hospital.persistence.dao;

import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistence.PatientRepository;
import com.my.project.petclinic.hospital.persistence.dao.resultSetExtractor.PatientResultSetExtractor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository(value = "jdbcPatientRepository")
class JdbcPatientRepository implements PatientRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PatientResultSetExtractor rowMapper;

    @Override
    public List<Patient> findAll() {
        return jdbcTemplate.query(Queries.SELECT_ALL_SQL, rowMapper);
    }
}
