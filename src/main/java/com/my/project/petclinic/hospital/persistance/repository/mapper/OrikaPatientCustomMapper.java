package com.my.project.petclinic.hospital.persistance.repository.mapper;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.domain.model.Patient;
import com.my.project.petclinic.hospital.persistance.entity.PatientEntity;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrikaPatientCustomMapper extends CustomMapper<PatientEntity, Patient> {

    @Override
    public void mapAtoB(PatientEntity patientEntity, Patient patient, MappingContext context) {
        final List<Doctor> patientList = patientEntity.getDoctorList().stream().
                map(d -> Doctor.builder().id(d.getId()).name(d.getName()).surName(d.getSurName()).position(d.getPosition()).build()).collect(Collectors.toList());
        patient.setDoctors(patientList);
    }
}
