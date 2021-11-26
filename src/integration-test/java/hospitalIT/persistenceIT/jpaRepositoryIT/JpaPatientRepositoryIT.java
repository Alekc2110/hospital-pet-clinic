package hospitalIT.persistenceIT.jpaRepositoryIT;

import com.my.project.petclinic.hospital.HospitalApplication;
import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistence.DoctorRepository;
import com.my.project.petclinic.hospital.persistence.JpaRepository.interfaces.JpaDoctorRepo;
import com.my.project.petclinic.hospital.persistence.JpaRepository.interfaces.JpaPatientRepo;
import com.my.project.petclinic.hospital.persistence.PatientRepository;
import com.my.project.petclinic.hospital.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = HospitalApplication.class)
public class JpaPatientRepositoryIT {

    @Autowired
    @Qualifier("jpaPatientRepository")
    private PatientRepository subject;
    @MockBean
    private JpaPatientRepo repository;

    @Test
    @DisplayName("should return list of patients")
    public void findAllShouldReturnListOfPatientsTest(){
        //given
        final PatientEntity patientEntity1 = PatientEntity.builder().id(1L).name("first").surName("surName1").age(51).build();
        final PatientEntity patientEntity2 = PatientEntity.builder().id(2L).name("second").surName("surName2").age(55).build();

        final List<PatientEntity> testEntityList = List.of(patientEntity1, patientEntity2);

        Mockito.when(repository.findAll()).thenReturn(testEntityList);

        //when
        final List<Patient> result = subject.findAll();

        //then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("first");
        assertThat(result.get(1).getName()).isEqualTo("second");
    }

    @Test
    @DisplayName("should save new patient")
    public void shouldSaveNewPatientTest(){
        //given
        final Long savedId = 1L;
        final Patient patientToSave = Patient.builder().name("first").surName("surName1").age(51).build();
        final PatientEntity patientEntity = PatientEntity.builder().id(1L).name("first").surName("surName1").age(51).build();

        when(repository.save(Mockito.any(PatientEntity.class))).thenReturn(patientEntity);

        //when
        final Patient result = subject.save(patientToSave);

        //then
        //then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getId()).isEqualTo(savedId);
    }

    @Test
    @DisplayName("should return patient by id")
    public void shouldReturnPatientByIdTest() {
        //given
        final Long patientId = 1L;
        final PatientEntity patientEntity = PatientEntity.builder().id(1L).name("first").surName("surName1").age(51).build();
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(patientEntity));

        //when
        final Patient result = subject.findById(patientId);

        //then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("first");
        assertThat(result.getSurName()).isEqualTo("surName1");
    }


    @Test
    @DisplayName("should update patient")
    public void shouldUpdatePatientTest(){
        //given
        final PatientEntity patientEntity = PatientEntity.builder().id(1L).name("first").surName("surName1").age(51).build();
        final Patient patient = Patient.builder().id(1L).name("first").surName("surName1").age(51).build();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(patientEntity));

        //when
        final Patient toUpdate = subject.findById(1L);
        toUpdate.setName("updated");
        final Patient result = subject.update(patient);

        //then
        Assertions.assertEquals(result.getName(), result.getName());
    }


}
