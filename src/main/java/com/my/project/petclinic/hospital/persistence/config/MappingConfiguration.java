package com.my.project.petclinic.hospital.persistence.config;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistence.entity.DoctorEntity;
import com.my.project.petclinic.hospital.persistence.entity.PatientEntity;
import com.my.project.petclinic.hospital.persistence.repository.mapper.MapStructDoctorMapper;
import com.my.project.petclinic.hospital.persistence.repository.mapper.MapStructPatientMapper;
import com.my.project.petclinic.hospital.persistence.repository.mapper.OrikaDoctorCustomMapper;
import com.my.project.petclinic.hospital.persistence.repository.mapper.OrikaPatientCustomMapper;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class MappingConfiguration {

    private final OrikaDoctorCustomMapper doctorMapper;
    private final OrikaPatientCustomMapper patientMapper;


    @Bean(name = "doctorJPAMapper")
    public MapperFacade doctorMapperFacade(MapperFactory mapperFactory) {
        mapperFactory.classMap(DoctorEntity.class, Doctor.class).customize(doctorMapper).byDefault().register();
        return mapperFactory.getMapperFacade();
    }

    @Bean(name = "patientJPAMapper")
    public MapperFacade patientMapperFacade(MapperFactory mapperFactory) {
        mapperFactory.classMap(PatientEntity.class, Patient.class).customize(patientMapper).byDefault().register();
        return mapperFactory.getMapperFacade();
    }
    @Bean
    public MapStructDoctorMapper mapStructDoctorMapper(){
        return Mappers.getMapper(MapStructDoctorMapper.class);
    }

    @Bean
    public MapStructPatientMapper mapStructPatientMapper(){
        return Mappers.getMapper(MapStructPatientMapper.class);
    }
}
