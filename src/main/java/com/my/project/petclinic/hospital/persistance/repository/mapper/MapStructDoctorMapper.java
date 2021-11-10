package com.my.project.petclinic.hospital.persistance.repository.mapper;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.persistance.entity.DoctorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructDoctorMapper {

    @Mapping(source = "doctor.patients", target = "patientList")
    DoctorEntity modelToEntity(Doctor doctor);

    @Mapping(source = "doctorEntity.patientList", target = "patients")
    Doctor entityToModel(DoctorEntity doctorEntity);
}
