package com.my.project.petclinic.hospital.persistance.entity;

import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "doctors")
@Entity
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "d_id", updatable = false, nullable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private String surName;
    @Column
    private String position;

    @ManyToMany(mappedBy = "doctorList")
    private List<PatientEntity> patientList = new ArrayList<>();

}
