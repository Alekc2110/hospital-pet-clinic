package com.my.project.petclinic.hospital.persistence.dao;

public final class Queries {
    public final static String SELECT_ALL_SQL = "select d.d_id as d_id, d.name as d_name, d.position as d_pos, d.sur_name as d_sn," +
            " p.p_id as p_id, p.name as p_name, p.sur_name as p_sn, p.age as p_age " +
            "from doctors d left join  patient_doctor pd on pd.d_id = d.d_id left join patients p on pd.p_id = p.p_id";
    public final static String SAVE_DOCTOR = "insert into doctors (name, position, sur_name) values (?,?,?)";
    public final static String UPDATE_DOCTOR = "update doctors set name=?, position=?, sur_name=? where d_id=?";
    public final static String SAVE_PATIENT = "insert into patients (age, name, sur_name) values (?,?,?)";
    public final static String UPDATE_PATIENT = "update patients set age=?, name=?, sur_name=? where p_id=?";
}
