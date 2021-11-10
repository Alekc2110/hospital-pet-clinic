package com.my.project.petclinic.hospital.api.mapper;


import com.my.project.petclinic.hospital.api.dto.DoctorDto;
import com.my.project.petclinic.hospital.domain.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorMapStructMapper {

    @Mapping(source = "doctor.patients", target = "patientDtoList")
    DoctorDto modelToDto(Doctor doctor);

    @Mapping(source = "dto.patientDtoList", target = "patients")
    Doctor dtoToModel(DoctorDto dto);

}
