package com.my.project.petclinic.hospital.persistence.dao;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.persistence.DoctorRepository;
import com.my.project.petclinic.hospital.persistence.dao.resultSetExtractor.DoctorResultSetExtractor;
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
@Repository(value = "jdbcDoctorRepository")
class JdbcDoctorRepository implements DoctorRepository {

    private final JdbcTemplate jdbcTemplate;
    private final DoctorResultSetExtractor rowMapper;

    @Override
    public List<Doctor> findAll() {
        return jdbcTemplate.query(Queries.SELECT_ALL_SQL, rowMapper);
    }

    @Override
    public Long save(Doctor doctor) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> keys;
        jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(Queries.SAVE_DOCTOR, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getPosition());
            ps.setString(3, doctor.getSurName());
            return ps;
        }, keyHolder);
        if (Objects.requireNonNull(keyHolder.getKeys()).size() > 1) {
            keys = keyHolder.getKeys();
            return ((Integer) keys.get("d_id")).longValue();
        }
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Doctor update(Doctor doctor) {
        jdbcTemplate.update(Queries.UPDATE_DOCTOR, doctor.getName(), doctor.getPosition(), doctor.getSurName(), doctor.getId());
        return Objects.requireNonNull(jdbcTemplate.query(Queries.SELECT_ALL_SQL, rowMapper))
                .stream().filter(p -> p.getId().equals(doctor.getId())).findAny().orElseThrow();

    }
}
