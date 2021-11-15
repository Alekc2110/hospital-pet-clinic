package com.my.project.petclinic.hospital.persistence.repository;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.persistence.DoctorRepository;
import com.my.project.petclinic.hospital.persistence.entity.DoctorEntity;
import com.my.project.petclinic.hospital.persistence.repository.interfaces.JpaDoctorRepo;
import com.my.project.petclinic.hospital.persistence.repository.mapper.MapStructDoctorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository(value = "jpaDoctorRepository")
class JpaDoctorRepository implements DoctorRepository {

    private final JpaDoctorRepo repository;
    private final MapStructDoctorMapper mapStructDoctorMapper;

    @Override
    public List<Doctor> findAll() {
        return repository.findAll().stream().map(mapStructDoctorMapper::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Long save(Doctor doctor) {
        return repository.save(mapStructDoctorMapper.modelToEntity(doctor)).getId();
    }

    @Override
    public Doctor update(Doctor doctor) {
        final DoctorEntity doctorEntity = repository.findById(doctor.getId()).
                orElseThrow(() -> new EntityNotFoundException(String.format("patient with id %s not found", doctor.getId())));

        doctorEntity.setName(doctor.getName());
        doctorEntity.setSurName(doctor.getSurName());
        doctorEntity.setPosition(doctor.getPosition());
        repository.flush();

        return mapStructDoctorMapper.entityToModel(doctorEntity);
    }
}
