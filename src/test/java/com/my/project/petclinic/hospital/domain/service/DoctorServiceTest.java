package com.my.project.petclinic.hospital.domain.service;

import com.my.project.petclinic.hospital.HospitalApplication;
import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.persistence.DoctorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = HospitalApplication.class)
public class DoctorServiceTest {

    @Autowired
    private DoctorService doctorService;
    @MockBean
    private DoctorRepository repository;

    private List<Doctor> testList;

    @BeforeEach
    void setUp() {
        testList = List.of(Doctor.builder().id(1L).name("first").surName("surName1").position("dentist").build(),
                Doctor.builder().id(2L).name("second").surName("surName2").position("therapist").build());
    }


    @Test
    @DisplayName("should return Optional of doctors list")
    public void getAllDoctorsTest() {
        //given
        Mockito.when(repository.findAll()).thenReturn(testList);
        //when
        final List<Doctor> allDoctors = doctorService.getAllDoctors();
        //then
        Assertions.assertEquals(2, allDoctors.size());
    }

    @Test
    @DisplayName("should save new doctor")
    public void saveDoctorTest() {
        //given
        Doctor doctor1 =  Doctor.builder().name("name").surName("SurName").position("doctor").build();
        Mockito.when(repository.save(doctor1)).thenReturn(3L);
        //when
        final Long savedId = doctorService.save(doctor1);
        //then
        Assertions.assertEquals(3L, savedId);
    }

    @Test
    @DisplayName("should update doctor")
    public void updateDoctorTest() {
        //given
        Doctor doctor =  Doctor.builder().id(1L).name("updated").surName("SurName").position("doctor").build();
        Doctor updated =  Doctor.builder().id(1L).name("updated").surName("SurName").position("doctor").build();
        Mockito.when(repository.update(doctor)).thenReturn(updated);
        //when
        final Doctor result = doctorService.update(doctor);
        //then
        Assertions.assertAll(()->{
            Assertions.assertEquals(updated.getName(), result.getName());
            Assertions.assertEquals(updated.getId(), result.getId());
            Assertions.assertEquals(updated.getPosition(),result.getPosition());
        });
    }

}
