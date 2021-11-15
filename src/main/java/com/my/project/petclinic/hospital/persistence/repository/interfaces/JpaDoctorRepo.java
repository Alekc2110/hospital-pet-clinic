package com.my.project.petclinic.hospital.persistence.repository.interfaces;

import com.my.project.petclinic.hospital.persistence.entity.DoctorEntity;
import lombok.NonNull;
import net.bytebuddy.TypeCache;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaDoctorRepo extends JpaRepository<DoctorEntity, Long> {
    @Override
    @EntityGraph(attributePaths = {"patientList"})
    @NonNull
    List<DoctorEntity> findAll();
}

