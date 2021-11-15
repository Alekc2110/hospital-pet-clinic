package com.my.project.petclinic.hospital.persistence.dao;

import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistence.PatientRepository;
import com.my.project.petclinic.hospital.persistence.dao.resultSetExtractor.PatientResultSetExtractor;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Repository(value = "jdbcPatientRepository")
class JdbcPatientRepository implements PatientRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PatientResultSetExtractor rowMapper;

    @Override
    public List<Patient> findAll() {
        return jdbcTemplate.query(Queries.SELECT_ALL_SQL, rowMapper);
    }

    @Override
    public Long save(Patient patient) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> keys;
        jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(Queries.SAVE_PATIENT, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, patient.getAge());
            ps.setString(2, patient.getName());
            ps.setString(3, patient.getSurName());
            return ps;
        }, keyHolder);
        if (Objects.requireNonNull(keyHolder.getKeys()).size() > 1) {
            keys = keyHolder.getKeys();
            return ((Integer) keys.get("p_id")).longValue();
        }
      return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Patient update(Patient patient) {
       jdbcTemplate.update(Queries.UPDATE_PATIENT, patient.getAge(), patient.getName(), patient.getSurName(), patient.getId());
                  return Objects.requireNonNull(jdbcTemplate.query(Queries.SELECT_ALL_SQL, rowMapper))
                    .stream().filter(p -> p.getId().equals(patient.getId())).findAny().orElseThrow();

    }
}
