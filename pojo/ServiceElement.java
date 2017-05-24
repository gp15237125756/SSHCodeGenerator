package com.ld.common.generator.pojo;

/**
 * SERVICE definition
 * @author Cruz
 *
 */
public class ServiceElement extends BaseXmlElement{
	/***
	 * service class name
	 */
	private String serviceName;
	
	private String daoName;
	
	private String searchDtoName;
	
	private String dtoName;
	
	private String searchMethodName;
	
	private String entityName;
	
	private String saveMethodName;
	
	private String keyGetMethod;
	
	private String keySetMethod;
	
	private String deleteMethodName;
	
	private String findMethodName;
	
	private String dtoFullName;
	
	private String daoFullName;
	
	private String searchDtoFullName;
	
	private String entityFullName;
	
	private String numberKey;

	private String remarks;
	
	public ServiceElement() {
		super();
	}


	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public String getNumberKey() {
		return numberKey;
	}



	public void setNumberKey(String numberKey) {
		this.numberKey = numberKey;
	}



	public String getServiceName() {
		return serviceName;
	}



	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}



	public String getDaoName() {
		return daoName;
	}



	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}



	public String getSearchDtoName() {
		return searchDtoName;
	}



	public void setSearchDtoName(String searchDtoName) {
		this.searchDtoName = searchDtoName;
	}



	public String getDtoName() {
		return dtoName;
	}



	public void setDtoName(String dtoName) {
		this.dtoName = dtoName;
	}



	public String getSearchMethodName() {
		return searchMethodName;
	}



	public String getDtoFullName() {
		return dtoFullName;
	}



	public void setDtoFullName(String dtoFullName) {
		this.dtoFullName = dtoFullName;
	}



	public String getDaoFullName() {
		return daoFullName;
	}



	public void setDaoFullName(String daoFullName) {
		this.daoFullName = daoFullName;
	}



	public String getSearchDtoFullName() {
		return searchDtoFullName;
	}



	public void setSearchDtoFullName(String searchDtoFullName) {
		this.searchDtoFullName = searchDtoFullName;
	}



	public String getEntityFullName() {
		return entityFullName;
	}



	public void setEntityFullName(String entityFullName) {
		this.entityFullName = entityFullName;
	}



	public void setSearchMethodName(String searchMethodName) {
		this.searchMethodName = searchMethodName;
	}



	public String getEntityName() {
		return entityName;
	}



	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}



	public String getSaveMethodName() {
		return saveMethodName;
	}



	public void setSaveMethodName(String saveMethodName) {
		this.saveMethodName = saveMethodName;
	}



	public String getKeyGetMethod() {
		return keyGetMethod;
	}



	public void setKeyGetMethod(String keyGetMethod) {
		this.keyGetMethod = keyGetMethod;
	}



	public String getKeySetMethod() {
		return keySetMethod;
	}



	public void setKeySetMethod(String keySetMethod) {
		this.keySetMethod = keySetMethod;
	}



	public String getDeleteMethodName() {
		return deleteMethodName;
	}



	public void setDeleteMethodName(String deleteMethodName) {
		this.deleteMethodName = deleteMethodName;
	}



	public String getFindMethodName() {
		return findMethodName;
	}



	public void setFindMethodName(String findMethodName) {
		this.findMethodName = findMethodName;
	}


	

	

	
	
	
}
