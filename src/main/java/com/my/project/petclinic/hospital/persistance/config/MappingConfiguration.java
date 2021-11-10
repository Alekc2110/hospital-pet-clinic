package com.my.project.petclinic.hospital.persistance.config;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistance.entity.DoctorEntity;
import com.my.project.petclinic.hospital.persistance.entity.PatientEntity;
import com.my.project.petclinic.hospital.persistance.repository.mapper.OrikaDoctorCustomMapper;
import com.my.project.petclinic.hospital.persistance.repository.mapper.OrikaPatientCustomMapper;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
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
}
