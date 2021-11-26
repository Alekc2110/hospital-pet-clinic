package hospitalIT.persistenceIT.jpaRepositoryIT;

import com.my.project.petclinic.hospital.HospitalApplication;
import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.persistence.DoctorRepository;
import com.my.project.petclinic.hospital.persistence.JpaRepository.interfaces.JpaDoctorRepo;
import com.my.project.petclinic.hospital.persistence.entity.DoctorEntity;
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
public class JpaDoctorRepositoryIT {

    @Autowired
    @Qualifier("jpaDoctorRepository")
    private DoctorRepository subject;
    @MockBean
    private JpaDoctorRepo repository;

    @Test
    @DisplayName("should return list of doctors")
    public void findAllShouldReturnListOfDoctorsTest(){
        //given
        final List<DoctorEntity> testEntityList = List.of(DoctorEntity.builder().id(1L).name("first").build(), DoctorEntity.builder().id(2L).name("second").build());
        when(repository.findAll()).thenReturn(testEntityList);

        //when
        final List<Doctor> result = subject.findAll();

        //then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("first");
        assertThat(result.get(1).getName()).isEqualTo("second");
    }

    @Test
    @DisplayName("should save new doctor")
    public void shouldSaveNewDoctorTest() {
        //given
        final Long savedId = 1L;
        final Doctor doctorToSave = Doctor.builder().name("first").surName("surName1").position("dentist").build();
        final DoctorEntity doctorEntity = DoctorEntity.builder().id(1L).name("first").surName("surName1").position("dentist").build();

        when(repository.save(Mockito.any(DoctorEntity.class))).thenReturn(doctorEntity);

        //when
        final Doctor result = subject.save(doctorToSave);

        //then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getId()).isEqualTo(savedId);
    }

    @Test
    @DisplayName("should return doctor by id")
    public void shouldReturnDoctorByIdTest() {
        //given
        final Long doctorId = 1L;
        final DoctorEntity doctorEntity = DoctorEntity.builder().id(1L).name("Max").surName("surName").position("surgeon").build();
        final Doctor doctor = Doctor.builder().id(1L).name("first").surName("surName1").position("dentist").build();

        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(doctorEntity));

        //when
        final Doctor result = subject.findById(doctorId);

        //then
      assertThat(result.getId()).isEqualTo(1L);
      assertThat(result.getName()).isEqualTo("Max");
      assertThat(result.getSurName()).isEqualTo("surName");
    }


    @Test
    @DisplayName("should update doctor")
    public void shouldUpdateDoctorTest() {
        //given
        final DoctorEntity doctorEntity = DoctorEntity.builder().id(1L).name("Max").surName("surName").position("surgeon").build();
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(doctorEntity));

        //when
        final Doctor toUpdate = subject.findById(1L);
        toUpdate.setName("updated");
        final Doctor result = subject.update(toUpdate);

        //then
       assertThat(result.getName()).isEqualTo("updated");
    }

}
