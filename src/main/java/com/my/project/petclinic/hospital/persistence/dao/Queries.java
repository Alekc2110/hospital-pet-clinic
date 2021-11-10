package com.my.project.petclinic.hospital.persistence.dao;

public final class Queries {
    public final static String SELECT_ALL_SQL = "select d.d_id as d_id, d.name as d_name, d.position as d_pos, d.sur_name as d_sn, p.p_id as p_id, p.name as p_name, p.sur_name as p_sn, p.age as p_age from patient_doctor pd join doctors d on pd.d_id = d.d_id join patients p on pd.p_id = p.p_id";
}
