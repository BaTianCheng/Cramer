package com.cw.cramer.workflow.engine.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * WfFormField
 * @author wicks
 */
public class WfFormField implements Serializable{
	
	private static final long serialVersionUID = 2991985286087528238L;

	protected String id;
	
	protected String name;
	
	protected String type;
	
	protected List<Map<String, String>> enumRange;
	
	protected Boolean isRequired;
	
	protected Boolean isReadable;
	
	protected Boolean isWritable;
	
	protected String value;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Map<String, String>> getEnumRange() {
		return enumRange;
	}

	public void setEnumRange(List<Map<String, String>> enumRange) {
		this.enumRange = enumRange;
	}

	public Boolean isRequired() {
		return isRequired;
	}

	public void setRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	public Boolean isReadable() {
		return isReadable;
	}

	public void setReadable(Boolean isReadable) {
		this.isReadable = isReadable;
	}

	public Boolean isWritable() {
		return isWritable;
	}

	public void setWritable(Boolean isWritable) {
		this.isWritable = isWritable;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
