package com.nky.sql;

import com.net.jfinal.JdbcSql;

public class VipSql extends JdbcSql{

	static{
		//sqlid  全名要规范  类名+名字
		jdbcSql.put("VipSql_index_getvip", "select t.id,t.vip_code,t.card_code,t.papers_num,t.mobile,t.heard_img_url,nick_name,sex,age,area,address,birthday,a.name as areaname from "
				+" (select * from t_vip where wxopenid=?) t left join t_area a on t.area=a.id");
		jdbcSql.put("VipSql_memberlist", "select distinct v.vip_code,v.card_code,v.nick_name,v.real_name,v.login_account,v.login_password,v.heard_img_url,f.family_pwd from"
				+" t_vip v, vip_family f  where  v.card_code=f.family_account and f.vip_code=?  ");
//		jdbcSql.put("VipSql_messagelist", "select dm.*,d.name as doctorname,date_format(dm.create_time,'%Y-%m-%d %H:%i:%s') create_timestr "
//				+" from doctor_vip_msg dm left join doctor d on dm.doctor_code=d.code where vip_code=? order by dm.id desc");
		jdbcSql.put("VipSql_messagelist", "select m.*,mc.send_time,v.vip_code from message m,message_center mc,t_vip v where m.id=mc.message_id  "
				+" and mc.reciver=v.id and v.vip_code=? and send_time>0 ${where} order by mc.send_time desc");

		//		select m.*,mc.send_time,v.vip_code from message m,message_center mc,t_vip v where m.id=mc.message_id 
//				and mc.reciver=v.id and v.vip_code='V10305' order by mc.send_time desc

	}
}