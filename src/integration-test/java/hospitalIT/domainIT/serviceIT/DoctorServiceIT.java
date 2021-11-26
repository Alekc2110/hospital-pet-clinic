package hospitalIT.domainIT.serviceIT;

import com.my.project.petclinic.hospital.HospitalApplication;
import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.domain.service.DoctorService;
import com.my.project.petclinic.hospital.persistence.config.RequestBackground;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = HospitalApplication.class)
public class DoctorServiceIT {
    @Autowired
    private DoctorService subject;

    @MockBean
    private RequestBackground condition;

    @BeforeEach
    public void setUp(){
        when(condition.getJdbc()).thenReturn(true);
    }

    @Test
    @DisplayName("should return list of doctors")
    public void getAllDoctorsShouldReturnListOfDoctorsTest() {
        //when
        final List<Doctor> allDoctors = subject.getAllDoctors();

        //then
        assertThat(allDoctors).isNotNull();
        assertThat(allDoctors.size()).isGreaterThan(1);
    }

    @Test
    @DisplayName("should save new doctor")
    public void shouldSaveNewDoctorTest() {
        //given
        Doctor doctor =  Doctor.builder().name("newDoctor").surName("SurName").position("doctor").build();
        //when
        final Doctor result = subject.save(doctor);
        //then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    @DisplayName("should update doctor")
    public void shouldUpdateDoctorTest() {
        //given
        final List<Doctor> allDoctors = subject.getAllDoctors();
        final Doctor doctorToUpdate = allDoctors.get(0);
        doctorToUpdate.setName("updatedDoctor");
        //when
        final Doctor result = subject.update(doctorToUpdate);
        //then
        assertThat(result.getId()).isEqualTo(doctorToUpdate.getId());
        assertThat(result.getName()).isEqualTo("updatedDoctor");
    }


}
