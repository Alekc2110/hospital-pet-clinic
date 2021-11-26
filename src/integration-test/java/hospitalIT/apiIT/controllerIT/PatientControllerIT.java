package hospitalIT.apiIT.controllerIT;

import com.my.project.petclinic.hospital.HospitalApplication;
import com.my.project.petclinic.hospital.api.controller.PatientController;
import com.my.project.petclinic.hospital.api.dto.PatientDto;
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
public class PatientControllerIT {
    @Autowired
    private PatientController subject;

    @MockBean
    private RequestBackground condition;

    @BeforeEach
    public void setUp() {
        when(condition.getJdbc()).thenReturn(true);
    }

    @Test
    @DisplayName("should return list of patientDtos")
    public void getAllShouldReturnPatientDtoListTest() {
        //when
        final List<PatientDto> result = subject.getAll();

        //then
        assertThat(result.size()).isGreaterThan(1);
    }

    @Test
    @DisplayName("should save new patient and return id")
    public void shouldSaveNewPatientTest() {
        //given
        final PatientDto patientDto = PatientDto.builder().name("newPatient").surName("surName1").age(27).build();

        //when
        final Long savedId = subject.save(patientDto);

        //then
        assertThat(savedId).isNotNull();
    }

    @Test
    @DisplayName("should update patient")
    public void shouldUpdateDoctorTest() {
        //given
        final PatientDto toUpdate = subject.getAll().get(0);
        toUpdate.setName("updatedPatient");

        //when
        final PatientDto result = subject.update(toUpdate.getId(), toUpdate);

        //then
        assertThat(result.getId()).isEqualTo(toUpdate.getId());
        assertThat(result.getName()).isEqualTo(toUpdate.getName());
    }

}
