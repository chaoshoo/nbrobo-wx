package com.net.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 区域
 * 
 * @author liuchang
 * 
 */
public class Area {
	
	private Long id;

	/** 名称 */
	private String name;

	/** 全称 */
	private String full_name;

	/** 树路径 */
	private String tree_path;

	/** 上级地区 */
	private Integer parent;

	/** 下级地区 */
	private Set<Area> children = new HashSet<Area>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取全称
	 * 
	 * @return 全称
	 */
	public String getFull_name() {
		return full_name;
	}

	/**
	 * 设置全称
	 * 
	 * @param fullName
	 *            全称
	 */
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	public String getTree_path() {
		return tree_path;
	}

	/**
	 * 设置树路径
	 * 
	 * @param treePath
	 *            树路径
	 */
	public void setTree_path(String tree_path) {
		this.tree_path = tree_path;
	}

	/**
	 * 获取上级地区
	 * 
	 * @return 上级地区
	 */
	public Integer getParent() {
		return parent;
	}

	/**
	 * 设置上级地区
	 * 
	 * @param parent
	 *            上级地区
	 */
	public void setParent(Integer parent) {
		this.parent = parent;
	}

	/**
	 * 获取下级地区
	 * 
	 * @return 下级地区
	 */
	public Set<Area> getChildren() {
		return children;
	}

	/**
	 * 设置下级地区
	 * 
	 * @param children
	 *            下级地区
	 */
	public void setChildren(Set<Area> children) {
		this.children = children;
	}

	/**
	 * 重写toString方法
	 * 
	 * @return 全称
	 */
	@Override
	public String toString() {
		return getFull_name();
	}

}
