package com.ld.common.generator.pojo;

/**
 * DAO definition
 * @author Cruz
 *
 */
public class DaoElement extends BaseXmlElement{

	private String entityName;
	
	private String daoName;
	
	private String primaryKeyType;
	
	private String remarks;
	
	
	
	public DaoElement() {
		super();
	}


	

	public DaoElement(String entityName, String daoName, String primaryKeyType,
			String remarks) {
		super();
		this.entityName = entityName;
		this.daoName = daoName;
		this.primaryKeyType = primaryKeyType;
		this.remarks = remarks;
	}




	public String getEntityName() {
		return entityName;
	}




	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}




	public String getDaoName() {
		return daoName;
	}




	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}




	public String getPrimaryKeyType() {
		return primaryKeyType;
	}




	public void setPrimaryKeyType(String primaryKeyType) {
		this.primaryKeyType = primaryKeyType;
	}




	public String getRemarks() {
		return remarks;
	}




	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}




	@Override
	public String toString() {
		return "DaoElement [entityName=" + entityName + ", daoName=" + daoName
				+ ", primaryKeyType=" + primaryKeyType + ", remarks=" + remarks
				+ "]";
	}

	

	
	
	
}
