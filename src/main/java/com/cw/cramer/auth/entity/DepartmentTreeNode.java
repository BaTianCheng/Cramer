package com.cw.cramer.auth.entity;

import java.util.List;

/**
 * 部门树节点结构
 * @author wicks
 */
public class DepartmentTreeNode {
	
	private String id;
	
	private String name;
	
	private boolean open;
	
	private List<DepartmentTreeNode> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public List<DepartmentTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<DepartmentTreeNode> children) {
		this.children = children;
	}

}
