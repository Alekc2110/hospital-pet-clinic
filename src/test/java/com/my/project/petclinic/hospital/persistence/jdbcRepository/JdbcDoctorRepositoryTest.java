package com.my.project.petclinic.hospital.persistence.jdbcRepository;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.persistence.jdbcRepository.keyHolder.KeyHolderFactory;
import com.my.project.petclinic.hospital.persistence.jdbcRepository.resultSetExtractor.DoctorResultSetExtractor;
import com.my.project.petclinic.hospital.persistence.jdbcRepository.resultSetExtractor.ListDoctorsResultSetExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JdbcDoctorRepositoryTest {

    @InjectMocks
    private JdbcDoctorRepository subject;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private ListDoctorsResultSetExtractor listResultSetExtractor;
    @Mock
    private DoctorResultSetExtractor doctorResultSetExtractor;
    @Mock
    private KeyHolderFactory keyHolderFactory;
    @Mock
    private KeyHolder keyHolder;

    @Test
    @DisplayName("should return list of doctors")
    public void findAllShouldReturnListOfDoctorsTest() {
        //given
        List<Doctor> testList = List.of(Doctor.builder().id(1L).name("first").surName("surName1").position("dentist").build(),
                Doctor.builder().id(2L).name("second").surName("surName2").position("therapist").build());
        Mockito.when(jdbcTemplate.query(Queries.SELECT_ALL_DOCTORS, listResultSetExtractor)).thenReturn(testList);

        //when
        final List<Doctor> result = subject.findAll();

        //then
        Assertions.assertEquals(testList, result);
    }

    @Test
    @DisplayName("should save new doctor")
    public void shouldSaveNewDoctorTest() {
        //given
        Long expectedId = 6L;
        Doctor doctor = Doctor.builder().name("Max").surName("SurName").position("surgeon").build();
        Doctor savedDoctor = Doctor.builder().id(6L).name("Max").surName("SurName").position("surgeon").build();
        Map<String, Object> keys = new HashMap<>();
        keys.put("d_id", expectedId);

        when(keyHolderFactory.newKeyHolder()).thenReturn(keyHolder);
        when(keyHolder.getKeys()).thenReturn(keys);
        when(keyHolder.getKey()).thenReturn(expectedId);
        when(jdbcTemplate.update(Mockito.any(PreparedStatementCreator.class), Mockito.any(KeyHolder.class))).thenReturn(1);
        when(jdbcTemplate.query(Queries.GET_DOCTOR_BY_ID, doctorResultSetExtractor, expectedId)).thenReturn(savedDoctor);

        //when
        final Doctor result = subject.save(doctor);

        //then
        assertEquals(expectedId, result.getId());
    }

    @Test
    @DisplayName("should update doctor")
    public void shouldUpdateDoctorTest() {
        //given
        Long doctorId = 3L;
        final Doctor doctor = Doctor.builder().id(3L).name("Max").surName("SurName").position("surgeon").build();
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyLong())).thenReturn(1);
        when(jdbcTemplate.query(Queries.GET_DOCTOR_BY_ID, doctorResultSetExtractor, doctorId)).thenReturn(doctor);

        //when
        final Doctor result = subject.update(doctor);

        //then
        Assertions.assertAll(() -> {
            Assertions.assertEquals(doctor.getId(), result.getId());
            Assertions.assertEquals(doctor.getName(), result.getName());
            Assertions.assertEquals(doctor.getSurName(), result.getSurName());
            Assertions.assertEquals(doctor.getPosition(), result.getPosition());
        });
    }

}
