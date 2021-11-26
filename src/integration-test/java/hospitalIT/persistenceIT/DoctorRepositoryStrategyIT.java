package hospitalIT.persistenceIT;

import com.my.project.petclinic.hospital.HospitalApplication;
import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.persistence.DoctorRepository;
import com.my.project.petclinic.hospital.persistence.config.RequestBackground;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = HospitalApplication.class)
public class DoctorRepositoryStrategyIT {

    @Autowired
    @Qualifier("doctorRepositoryStrategy")
    private DoctorRepository subject;

    @MockBean
    private RequestBackground condition;

    @Test
    @DisplayName("should return list of doctors using jdbc")
    public void shouldReturnListOfDoctorsByJdbcIfConditionTrueTest() {
        //given
        when(condition.getJdbc()).thenReturn(true);

        //when
        final List<Doctor> result = subject.findAll();

        //then
       assertThat(result).hasSizeGreaterThan(0);
    }

    @Test
    @DisplayName("should return list of doctors using jpa")
    public void shouldReturnListOfDoctorsByJpaIfConditionFalseTest() {
        //given
        when(condition.getJdbc()).thenReturn(false);

        //when
        final List<Doctor> result = subject.findAll();

        //then
        assertThat(result).hasSizeGreaterThan(0);
    }

    @Test
    @DisplayName("should save new doctor using jdbc")
    public void shouldSaveNewDoctorByJdbcIfConditionTrueTest() {
        //given
        final Doctor doctor = Doctor.builder().name("first").surName("surName1").position("dentist").build();
        when(condition.getJdbc()).thenReturn(true);

        //when
        final Doctor saved = subject.save(doctor);

        //then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getId()).isNotZero();
    }

    @Test
    @DisplayName("should save new doctor using jpa")
    public void shouldSaveNewDoctorByJpaIfConditionFalseTest() {
        //given
        final Doctor doctor = Doctor.builder().name("first").surName("surName1").position("dentist").build();
        when(condition.getJdbc()).thenReturn(false);

        //when
        final Doctor saved = subject.save(doctor);

        //then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getId()).isNotZero();
    }

    @Test
    @DisplayName("should find by id and return doctor using jpa")
    public void shouldFindByIdAndReturnDoctorByJpaIfConditionFalseTest() {
        //given
        final Long id = 3L;
        when(condition.getJdbc()).thenReturn(false);

        //when
        final Doctor doctor = subject.findById(id);

        //then
        assertThat(doctor).isNotNull();

    }

    @Test
    @DisplayName("should find by id and return doctor using jdbc")
    public void shouldFindByIdAndReturnDoctorByJdbcIfConditionTrueTest() {
        //given
        final Long id = 3L;
        when(condition.getJdbc()).thenReturn(true);

        //when
        final Doctor doctor = subject.findById(id);

        //then
        assertThat(doctor).isNotNull().isExactlyInstanceOf(Doctor.class);
    }

    @Test
    @DisplayName("should update doctor using jdbc")
    public void shouldUpdateDoctorByJdbcIfConditionTrueTest() {
        //given
        when(condition.getJdbc()).thenReturn(true);

        //when
        final Doctor toUpdate = subject.findById(1L);
        toUpdate.setName("updated");
        final Doctor updated = subject.update(toUpdate);

        //then
        assertThat(updated.getName()).isEqualTo("updated");
    }

    @Test
    @DisplayName("should update doctor using jpa")
    public void shouldUpdateDoctorByJpaIfConditionFalseTest() {
        //given
        when(condition.getJdbc()).thenReturn(false);

        //when
        final Doctor toUpdate = subject.findById(2L);
        toUpdate.setName("updated");
        final Doctor updated = subject.update(toUpdate);

        //then
        assertThat(updated.getName()).isEqualTo("updated");
    }




}
