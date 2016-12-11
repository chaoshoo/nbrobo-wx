package com.net.jfinal;

import com.net.util.Reflections;

public class JFinalEntity implements java.io.Serializable{
	private static final long serialVersionUID = 10987654332L;
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String tableName() throws Exception {
		return Reflections.getAnnotationTableBind(getClass());
	}
}
