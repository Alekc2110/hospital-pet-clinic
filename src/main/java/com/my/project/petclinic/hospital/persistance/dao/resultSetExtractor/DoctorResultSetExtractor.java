package com.my.project.petclinic.hospital.persistance.dao.resultSetExtractor;

import com.my.project.petclinic.hospital.domain.model.Doctor;
import com.my.project.petclinic.hospital.domain.model.Patient;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorResultSetExtractor implements ResultSetExtractor<List<Doctor>> {
    @Override
    public List<Doctor> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Doctor> doctorList = new ArrayList<>();
        List<Patient> patientList = new ArrayList<>();
        Map<Long, Doctor> doctorKeyDoctorMap = new HashMap<>();
        Map<Long, Patient> patientKeyPatientMap = new HashMap<>();

        while (rs.next()) {
            final Long doctorKey = rs.getLong("d_id");
            Doctor doctor = doctorKeyDoctorMap.get(doctorKey);
            if (doctor == null) {
                doctor = new Doctor();
                doctorList.add(doctor);
                doctor.setId(doctorKey);
                doctor.setName(rs.getString("d_name"));
                doctor.setPosition(rs.getString("d_pos"));
                doctor.setSurName(rs.getString("d_sn"));
                doctorKeyDoctorMap.put(doctorKey, doctor);
            }
            final Long patientKey = rs.getLong("p_id");
            Patient patient = patientKeyPatientMap.get(patientKey);
            if (patient == null) {
                patient = new Patient();
                patientList.add(patient);
                patient.setId(patientKey);
                patient.setName(rs.getString("p_name"));
                patient.setSurName(rs.getString("p_sn"));
                patient.setAge(rs.getInt("p_age"));
                patientKeyPatientMap.put(patientKey, patient);

            }
            if (doctor.getPatients() == null) {
                doctor.setPatients(patientList);
            }
            doctor.getPatients().add(patient);

        }

        return doctorList;
    }
}
