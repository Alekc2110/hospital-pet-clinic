package com.my.project.petclinic.hospital.domain.service;

import com.my.project.petclinic.hospital.HospitalApplication;
import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistence.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest(classes = HospitalApplication.class)
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @MockBean
    private PatientRepository repository;

    private List<Patient> testList;

    @BeforeEach
    void setUp() {
        testList = List.of(Patient.builder().id(1L).name("first").surName("surName1").age(40).build(),
                Patient.builder().id(2L).name("second").surName("surName2").age(35).build());
    }

    @Test
    @DisplayName("should return Optional of patient list")
    public void getAllPatientsTest() {
        //given
        Mockito.when(repository.findAll()).thenReturn(testList);
        //when
        final List<Patient> allPatients = patientService.getAllPatients();
        //then
        Assertions.assertEquals(2, allPatients.size());
    }

    @Test
    @DisplayName("should save new patient")
    public void saveDoctorTest() {
        //given
        Patient patient1 =  Patient.builder().name("name").surName("SurName").age(25).build();
        Mockito.when(repository.save(patient1)).thenReturn(5L);
        //when
        final Long savedId = patientService.save(patient1);
        //then
        Assertions.assertEquals(5L, savedId);

    }



}
