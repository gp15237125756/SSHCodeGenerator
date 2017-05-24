package com.ld.common.generator.pojo;

import java.util.List;

/**
 * DTO definition
 * @author Cruz
 *
 */
public class DtoElement extends BaseXmlElement{

	private String dtoName;
	
	private boolean hasDateColumn;
	
	private boolean hasBigDecimalColumn;
	
	private boolean hasSetColumn;
	
	private boolean hasListColumn;
	
	private String remarks;
	
	private List<ColumnProperty> properties;
	
	private List<String> setParameterTypelist;
	
	private List<String> listParameterTypelist;
	

	public DtoElement() {
		super();
	}

	public String getDtoName() {
		return dtoName;
	}

	public void setDtoName(String dtoName) {
		this.dtoName = dtoName;
	}

	public boolean isHasDateColumn() {
		return hasDateColumn;
	}

	public void setHasDateColumn(boolean hasDateColumn) {
		this.hasDateColumn = hasDateColumn;
	}

	public boolean isHasBigDecimalColumn() {
		return hasBigDecimalColumn;
	}

	public void setHasBigDecimalColumn(boolean hasBigDecimalColumn) {
		this.hasBigDecimalColumn = hasBigDecimalColumn;
	}

	public boolean isHasSetColumn() {
		return hasSetColumn;
	}

	public void setHasSetColumn(boolean hasSetColumn) {
		this.hasSetColumn = hasSetColumn;
	}

	public boolean isHasListColumn() {
		return hasListColumn;
	}

	public void setHasListColumn(boolean hasListColumn) {
		this.hasListColumn = hasListColumn;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<ColumnProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<ColumnProperty> properties) {
		this.properties = properties;
	}

	public List<String> getSetParameterTypelist() {
		return setParameterTypelist;
	}

	public void setSetParameterTypelist(List<String> setParameterTypelist) {
		this.setParameterTypelist = setParameterTypelist;
	}

	public List<String> getListParameterTypelist() {
		return listParameterTypelist;
	}

	public void setListParameterTypelist(List<String> listParameterTypelist) {
		this.listParameterTypelist = listParameterTypelist;
	}
	
	
}
