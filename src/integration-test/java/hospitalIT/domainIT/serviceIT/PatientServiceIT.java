package hospitalIT.domainIT.serviceIT;

import com.my.project.petclinic.hospital.HospitalApplication;
import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.domain.service.PatientService;
import com.my.project.petclinic.hospital.persistence.config.RequestBackground;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = HospitalApplication.class)
public class PatientServiceIT {
    @Autowired
    private PatientService subject;

    @MockBean
    private RequestBackground condition;

    @BeforeEach
    public void setUp(){
        when(condition.getJdbc()).thenReturn(true);
    }

    @Test
    @DisplayName("should return patient list")
    public void getAllPatientsShouldReturnPatientsListTest() {
        //when
        final List<Patient> allPatients = subject.getAllPatients();
        //then
        assertThat(allPatients).isNotNull();
        assertThat(allPatients.size()).isGreaterThan(1);
    }

    @Test
    @DisplayName("should save new patient")
    public void shouldSaveNewPatientTest() {
        //given
        Patient newPatient =  Patient.builder().name("new").surName("SurName").age(25).build();

        //when
        final Patient result = subject.save(newPatient);

        //then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    @DisplayName("should update patient")
    public void shouldUpdatePatientTest() {
        //given
        final Patient patient = subject.getAllPatients().get(0);
        patient.setName("updatedPatient");

        //when
        final Patient result = subject.update(patient);

        //then
        assertThat(result.getId()).isEqualTo(patient.getId());
        assertThat(result.getName()).isEqualTo("updatedPatient");
    }

}
