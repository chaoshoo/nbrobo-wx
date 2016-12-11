package com.net.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * region
 * 
 * @author liuchang
 * 
 */
public class Area {
	
	private Long id;

	/** Name */
	private String name;

	/** Full name */
	private String full_name;

	/** Tree path */
	private String tree_path;

	/** Superior area */
	private Integer parent;

	/** Subordinate area */
	private Set<Area> children = new HashSet<Area>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get name
	 * 
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name
	 * 
	 * @param name
	 *            Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get full name
	 * 
	 * @return Full name
	 */
	public String getFull_name() {
		return full_name;
	}

	/**
	 * Set full name
	 * 
	 * @param fullName
	 *            Full name
	 */
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	/**
	 * Get tree path
	 * 
	 * @return Tree path
	 */
	public String getTree_path() {
		return tree_path;
	}

	/**
	 * Set tree path
	 * 
	 * @param treePath
	 *            Tree path
	 */
	public void setTree_path(String tree_path) {
		this.tree_path = tree_path;
	}

	/**
	 * Get higher area
	 * 
	 * @return Superior area
	 */
	public Integer getParent() {
		return parent;
	}

	/**
	 * Set up area
	 * 
	 * @param parent
	 *            Superior area
	 */
	public void setParent(Integer parent) {
		this.parent = parent;
	}

	/**
	 * Get lower area
	 * 
	 * @return Subordinate area
	 */
	public Set<Area> getChildren() {
		return children;
	}

	/**
	 * Set lower area
	 * 
	 * @param children
	 *            Subordinate area
	 */
	public void setChildren(Set<Area> children) {
		this.children = children;
	}

	/**
	 * RewritetoStringMethod
	 * 
	 * @return Full name
	 */
	@Override
	public String toString() {
		return getFull_name();
	}

}