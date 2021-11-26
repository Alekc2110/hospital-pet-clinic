package hospitalIT.persistenceIT;

import com.my.project.petclinic.hospital.HospitalApplication;
import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistence.DoctorRepository;
import com.my.project.petclinic.hospital.persistence.PatientRepository;
import com.my.project.petclinic.hospital.persistence.config.RequestBackground;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = HospitalApplication.class)
public class PatientRepositoryStrategyIT {

    @Autowired
    @Qualifier("patientRepositoryStrategy")
    private PatientRepository subject;

    @MockBean
    private RequestBackground condition;

    @Test
    @DisplayName("should return list of patients using jdbc")
    public void shouldReturnListOfPatientsByJdbcIfConditionTrueTest() {
        //given
        when(condition.getJdbc()).thenReturn(true);

        //when
        final List<Patient> result = subject.findAll();

        //then
        assertThat(result).hasSizeGreaterThan(0);
    }
    @Test
    @DisplayName("should return list of patients using jpa")
    public void shouldReturnListOfPatientsByJpaIfConditionFalseTest() {
        //given
        when(condition.getJdbc()).thenReturn(false);

        //when
        final List<Patient> result = subject.findAll();

        //then
        assertThat(result).hasSizeGreaterThan(0);
    }

    @Test
    @DisplayName("should save new patient using jdbc")
    public void shouldSaveNewDoctorByJdbcIfConditionTrueTest() {
        //given
        final Patient patient = Patient.builder().name("firstJdbc").surName("surName1").build();
        when(condition.getJdbc()).thenReturn(true);

        //when
        final Patient saved = subject.save(patient);

        //then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getId()).isNotZero();
    }

    @Test
    @DisplayName("should save new patient using jpa")
    public void shouldSaveNewPatientByJpaIfConditionFalseTest() {
        //given
        final Patient patient = Patient.builder().name("firstJpa").surName("surName2").build();
        when(condition.getJdbc()).thenReturn(false);

        //when
        final Patient saved = subject.save(patient);

        //then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getId()).isNotZero();
    }

    @Test
    @DisplayName("should find by id and return patient using jpa")
    public void shouldFindByIdAndReturnPatientByJpaIfConditionFalseTest() {
        //given
        final Long id = 1L;
        when(condition.getJdbc()).thenReturn(false);

        //when
        final Patient patient = subject.findById(id);

        //then
        assertThat(patient).isNotNull();
    }

    @Test
    @DisplayName("should find by id and return patient using jdbc")
    public void shouldFindByIdAndReturnPatientByJdbcIfConditionTrueTest() {
        //given
        final Long id = 1L;
        when(condition.getJdbc()).thenReturn(true);

        //when
        final Patient patient = subject.findById(id);

        //then
        assertThat(patient).isNotNull();
    }

    @Test
    @DisplayName("should update patient using jdbc")
    public void shouldUpdatePatientByJdbcIfConditionTrueTest() {
        //given
        when(condition.getJdbc()).thenReturn(true);

        //when
        final Patient toUpdate = subject.findById(1L);
        toUpdate.setName("updated");
        final Patient updated = subject.update(toUpdate);

        //then
        assertThat(updated.getName()).isNotEmpty().isEqualTo("updated");
    }

    @Test
    @DisplayName("should update patient using jpa")
    public void shouldUpdatePatientByJpaIfConditionFalseTest() {
        //given
        when(condition.getJdbc()).thenReturn(false);

        //when
        final Patient toUpdate = subject.findById(2L);
        toUpdate.setName("updated");
        final Patient updated = subject.update(toUpdate);

        //then
        assertThat(updated.getName()).isNotEmpty().isEqualTo("updated");
    }
}
