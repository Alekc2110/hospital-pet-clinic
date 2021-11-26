package hospitalIT.apiIT.controllerIT;

import com.my.project.petclinic.hospital.HospitalApplication;
import com.my.project.petclinic.hospital.api.controller.DoctorController;
import com.my.project.petclinic.hospital.api.dto.DoctorDto;
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
public class DoctorControllerIT {
    @Autowired
    private DoctorController subject;

    @MockBean
    private RequestBackground condition;

    @BeforeEach
    public void setUp() {
        when(condition.getJdbc()).thenReturn(true);
    }

    @Test
    @DisplayName("should return list of doctorDtos")
    public void getAllShouldReturnDoctorDtoListTest() {
        //when
        final List<DoctorDto> result = subject.getAll();

        //then
        assertThat(result.size()).isGreaterThan(1);
    }

    @Test
    @DisplayName("should save new doctor and return id")
    public void shouldSaveNewDoctorTest() {
        //given
        final DoctorDto doctorDto = DoctorDto.builder().name("newOne").surName("surName1").position("dentist").build();

        //when
        final Long savedId = subject.save(doctorDto);

        //then
        assertThat(savedId).isNotNull();
    }

    @Test
    @DisplayName("should update doctor")
    public void shouldUpdateDoctorTest() {
        //given
        final DoctorDto toUpdate = subject.getAll().get(0);
        toUpdate.setPosition("unknown");

        //when
        final DoctorDto result = subject.update(toUpdate.getId(), toUpdate);

        //then
        assertThat(result.getId()).isEqualTo(toUpdate.getId());
        assertThat(result.getPosition()).isEqualTo("unknown");
    }

}
