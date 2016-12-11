package com.nky.sql;

import com.net.jfinal.JdbcSql;

public class DoctorSql extends JdbcSql{

	static{
		//sqlid  全名要规范  类名+名字
		jdbcSql.put("DoctorSql_list", "select d.id,d.name,d.code,d.sex,d.hospital_code,d.office_code,d.edu,d.title,d.special,d.pic,h.name as hospitalname  "
				+ " from doctor d,hospital h where d.hospital_code=h.code ${where}");
		jdbcSql.put("DoctorSql_mylist", "select d.*,h.name as hospitalname from (select d.id,d.name,d.code,d.sex,d.hospital_code,d.office_code,d.edu,d.title,d.special,d.pic  "
				+ "  from doctor d,doctor_vip v where d.code=v.doctor_code and v.vip_code=?) d,hospital h where d.hospital_code=h.code ${where}");
		}
}