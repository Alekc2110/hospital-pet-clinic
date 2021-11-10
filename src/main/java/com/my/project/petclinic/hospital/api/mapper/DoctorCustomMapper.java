package com.my.project.petclinic.hospital.api.mapper;

import com.my.project.petclinic.hospital.api.dto.DoctorDto;
import com.my.project.petclinic.hospital.api.dto.PatientDto;
import com.my.project.petclinic.hospital.domain.model.Doctor;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorCustomMapper extends CustomMapper<Doctor, DoctorDto> {

    @Override
    public void mapAtoB(Doctor doctor, DoctorDto doctorDto, MappingContext context) {
        final List<PatientDto> patientDtoList = doctor.getPatients().stream().map(patient -> PatientDto.builder().id(patient.getId()).build()).collect(Collectors.toList());
        doctorDto.setPatientDtoList(patientDtoList);
    }
}
