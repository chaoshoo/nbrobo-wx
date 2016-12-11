package com.nky.sql;

import com.net.jfinal.JdbcSql;

public class YuYueSql extends JdbcSql{

	static{
		//sqlid  全名要规范  类名+名字
		jdbcSql.put("YuYueSql_replaydoctor", "select q.*,d.name as doctorname from "
				+ " (select max(id) id,doctor_code from vip_questions where vip_code=? group by doctor_code) q,doctor d "
				+ " where q.doctor_code=d.code ${where} order by q.id desc");
		jdbcSql.put("YuYueSql_yuyuedoctor", "select i.*,d.name as doctorname,date_format(i.order_time,'%Y-%m-%d %H:%i:%s') order_timestr from remote_inspect i,doctor d ,"
				+" (select doctor_code,max(id) as maxid from remote_inspect where vip_code=? group by doctor_code ) m"
				+ " where i.id=m.maxid and i.doctor_code=d.code ${where} order by i.id desc");
		jdbcSql.put("YuYueSql_replaylast", "select q.*,date_format(create_time,'%Y-%m-%d %H:%i:%s') create_timestr from vip_questions_log q,"
										+ " (select max(id) id,vip_questions_id from vip_questions_log where vip_questions_id in (${where}) "
										+ " group by vip_questions_id) t where q.id=t.id");
		jdbcSql.put("YuYueSql_yuyuehis", "select i.*,date_format(i.order_time,'%Y-%m-%d %H:%i:%s') order_timestr ,date_format(t.sp_time,'%Y-%m-%d %H:%i:%s') create_timestr  from remote_inspect i left join  "
				+" (select max(create_time) as sp_time,remote_inspect_code  from remote_inspect_log group by remote_inspect_code) t "
				+ " on i.code=t.remote_inspect_code where vip_code=? and doctor_code=? order by i.id desc");
		jdbcSql.put("YuYueSql_replayhis", "select  q.id,q.create_time,q.answer_code,q.answer_content,date_format(q.create_time,'%Y-%m-%d %H:%i:%s') create_timestr,d.name as doctorname from"
				+ " vip_questions_log q left join doctor d on q.answer_code=d.code where vip_questions_id =? order by q.id desc");
		}
}