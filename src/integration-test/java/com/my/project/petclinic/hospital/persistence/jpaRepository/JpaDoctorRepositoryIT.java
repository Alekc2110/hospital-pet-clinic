package com.my.project.petclinic.hospital.persistence.jpaRepository;

import com.my.project.petclinic.hospital.HospitalApplication;
import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.persistence.DoctorRepository;
import com.my.project.petclinic.hospital.persistence.JpaRepository.interfaces.JpaDoctorRepo;
import com.my.project.petclinic.hospital.persistence.entity.DoctorEntity;
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
}
