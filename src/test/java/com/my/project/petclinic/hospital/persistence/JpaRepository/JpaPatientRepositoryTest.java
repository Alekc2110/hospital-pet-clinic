package com.my.project.petclinic.hospital.persistence.JpaRepository;

import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistence.entity.PatientEntity;
import com.my.project.petclinic.hospital.persistence.JpaRepository.interfaces.JpaPatientRepo;
import com.my.project.petclinic.hospital.persistence.JpaRepository.mapper.MapStructPatientMapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JpaPatientRepositoryTest {

    @InjectMocks
    private JpaPatientRepository subject;
    @Mock
    private JpaPatientRepo repository;
    @Mock
    private MapStructPatientMapperImpl mapStructPatientMapper;


    private List<PatientEntity> testEntityList;
    private List<Patient> testUpdatedList;
    private Patient patient;

    @BeforeEach
    void setUp() {

        testUpdatedList =  List.of(Patient.builder().id(1L).name("first").surName("surName1").build(),
                Patient.builder().id(2L).name("second").surName("surName2").build(),
                Patient.builder().id(3L).name("Max").surName("SurName").build());

        patient =  Patient.builder().name("Max").surName("SurName").age(50).build();

    }

    @Test
    @DisplayName("should return list of patients")
    public void findAllShouldReturnListOfPatientsTest(){
        //given
        final PatientEntity patientEntity = PatientEntity.builder().id(1L).name("first").surName("surName1").age(51).build();
        final Patient patient = Patient.builder().id(1L).name("first").surName("surName1").age(51).build();
        final List<PatientEntity> testEntityList = List.of(patientEntity);

        Mockito.when(repository.findAll()).thenReturn(testEntityList);
        Mockito.when(mapStructPatientMapper.entityToModel(patientEntity)).thenReturn(patient);

        //when
        final List<Patient> result = subject.findAll();

        //then
        Assertions.assertAll(()->{
            Assertions.assertEquals(testEntityList.size(), result.size());
            Assertions.assertEquals(testEntityList.get(0).getId(), result.get(0).getId());
            Assertions.assertEquals(testEntityList.get(0).getName(), result.get(0).getName());
            Assertions.assertEquals(testEntityList.get(0).getAge(), result.get(0).getAge());
        });
    }

    @Test
    @DisplayName("should save new patient")
    public void shouldSaveNewPatientTest(){
        //given
        final Patient patientToSave = Patient.builder().name("first").surName("surName1").age(51).build();
        final PatientEntity patientEntity = PatientEntity.builder().id(1L).name("first").surName("surName1").age(51).build();
        final Patient patient = Patient.builder().id(1L).name("first").surName("surName1").age(51).build();

        when(mapStructPatientMapper.modelToEntity(patientToSave)).thenReturn(patientEntity);
        when(repository.save(Mockito.any(PatientEntity.class))).thenReturn(patientEntity);
        when(mapStructPatientMapper.entityToModel(patientEntity)).thenReturn(patient);

        //when
        final Patient result = subject.save(patientToSave);

        //then
       Assertions.assertEquals(patient.getId(), result.getId());
    }

    @Test
    @DisplayName("should update patient")
    public void shouldUpdatePatientTest(){
        //given
        final PatientEntity patientEntity = PatientEntity.builder().id(1L).name("first").surName("surName1").age(51).build();
        final Patient patient = Patient.builder().id(1L).name("first").surName("surName1").age(51).build();
        final Patient updatedPatient = Patient.builder().id(1L).name("second").surName("surName2").age(53).build();
        final PatientEntity patientUpdatedEntity = PatientEntity.builder().id(1L).name("second").surName("surName2").age(53).build();

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(patientEntity));
        Mockito.when(mapStructPatientMapper.entityToModel(patientEntity)).thenReturn(updatedPatient);
        when(mapStructPatientMapper.modelToEntity(updatedPatient)).thenReturn(patientUpdatedEntity);
        when(mapStructPatientMapper.entityToModel(patientUpdatedEntity)).thenReturn(updatedPatient);

        //when
        final Patient result = subject.update(patient);

        //then
        Assertions.assertEquals(updatedPatient.getName(), result.getName());
    }
}
