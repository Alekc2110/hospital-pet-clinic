package hospitalIT.persistenceIT.jdbcRepositoryIT;

import com.my.project.petclinic.hospital.HospitalApplication;
import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.persistence.DoctorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = HospitalApplication.class)
public class JdbcDoctorRepositoryIT {

    @Autowired
    @Qualifier("jdbcDoctorRepository")
    private DoctorRepository subject;

    @Test
    @DisplayName("should return list of doctors")
    public void findAllShouldReturnListOfDoctorsTest() {
        //when
        final List<Doctor> result = subject.findAll();

        //then
        assertThat(result).hasSizeGreaterThan(0);
    }

    @Test
    @DisplayName("should save new doctor")
    public void shouldSaveNewDoctorTest() {
        //given
        Doctor doctor = Doctor.builder().name("Max").surName("SurName").position("surgeon").build();

        //when
        final Doctor result = subject.save(doctor);

        //then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    @DisplayName("should return doctor by id")
    public void shouldReturnDoctorByIdTest() {
        //given
        Long doctorId = 3L;

        //when
        final Doctor result = subject.findById(doctorId);

        //then
        assertThat(result.getId()).isNotNull().isEqualTo(3L);
    }


    @Test
    @DisplayName("should update doctor")
    public void shouldUpdateDoctorTest() {
        //given
        Long doctorId = 1L;

        //when
        final Doctor toUpdate = subject.findById(doctorId);
        toUpdate.setName("updated");
        final Doctor result = subject.update(toUpdate);

        //then
        assertThat(result.getName()).isEqualTo("updated");
    }

}
