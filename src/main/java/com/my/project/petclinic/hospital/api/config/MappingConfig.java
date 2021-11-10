package com.my.project.petclinic.hospital.api.config;

import com.my.project.petclinic.hospital.api.dto.DoctorDto;
import com.my.project.petclinic.hospital.api.dto.PatientDto;
import com.my.project.petclinic.hospital.api.mapper.DoctorCustomMapper;
import com.my.project.petclinic.hospital.api.mapper.PatientCustomMapper;
import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.domain.model.Patient;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MappingConfig {

    private final DoctorCustomMapper dMapper;
    private final PatientCustomMapper pMapper;

    public MappingConfig(DoctorCustomMapper dMapper, PatientCustomMapper pMapper) {
        this.dMapper = dMapper;
        this.pMapper = pMapper;
    }

    @Bean(name = "doctorMapper")
    public MapperFacade doctorMapperFacade(MapperFactory mapperFactory) {
        mapperFactory.classMap(Doctor.class, DoctorDto.class).customize(dMapper).byDefault().register();
        return mapperFactory.getMapperFacade();
    }

    @Bean(name = "patientMapper")
    public MapperFacade patientMapperFacade(MapperFactory mapperFactory) {
        mapperFactory.classMap(Patient.class, PatientDto.class).customize(pMapper).byDefault().register();
        return mapperFactory.getMapperFacade();
    }

}
