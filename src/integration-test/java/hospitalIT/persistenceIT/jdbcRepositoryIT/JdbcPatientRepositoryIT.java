package hospitalIT.persistenceIT.jdbcRepositoryIT;

import com.my.project.petclinic.hospital.HospitalApplication;
import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistence.DoctorRepository;
import com.my.project.petclinic.hospital.persistence.PatientRepository;
import com.my.project.petclinic.hospital.persistence.entity.PatientEntity;
import com.my.project.petclinic.hospital.persistence.jdbcRepository.Queries;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = HospitalApplication.class)
public class JdbcPatientRepositoryIT {
    @Autowired
    @Qualifier("jdbcPatientRepository")
    private PatientRepository subject;

    @Test
    @DisplayName("should return list of patients")
    public void findAllShouldReturnListOfPatientsTest(){
        //when
        final List<Patient> result = subject.findAll();

        //then
        assertThat(result).hasSizeGreaterThan(0);
    }

    @Test
    @DisplayName("should save new patient")
    public void shouldSaveNewPatientTest(){
        //given
        final Patient patientToSave = Patient.builder().name("first").surName("surName1").age(51).build();

        //when
        final Patient result = subject.save(patientToSave);

        //then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    @DisplayName("should return patient by id")
    public void shouldReturnPatientByIdTest(){
        //given
        final Long patientId = 3L;

        //when
        final Patient result = subject.findById(patientId);

        //then
        assertThat(result.getId()).isNotNull().isEqualTo(3L);
    }

    @Test
    @DisplayName("should update patient")
    public void shouldUpdateDoctorTest(){
        //given
        final Long patientId = 3L;

        //when
        final Patient toUpdate = subject.findById(patientId);
        toUpdate.setName("updated");
        final Patient result = subject.update(toUpdate);

        //then
        assertThat(result.getName()).isEqualTo("updated");
    }




}
