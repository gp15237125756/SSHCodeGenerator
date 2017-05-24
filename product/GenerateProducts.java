package com.ld.common.generator.product;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.ld.common.generator.GeneratorConstant;
import com.ld.common.generator.configuration.Configuration;
import com.ld.common.generator.pojo.BaseXmlElement;
import com.ld.common.generator.pojo.DaoElement;
import com.ld.common.generator.pojo.DtoElement;
import com.ld.common.generator.pojo.ServiceElement;

public class GenerateProducts {
	 private static final Log LOGGER  = LogFactory.getLog(GenerateProducts.class);
	 
	 private final Configuration cfg;
	 
	 private GenerateProducts() throws Exception{
		 cfg=new Configuration();
		 cfg.loadConfiguration();
	 }
	 
	 
	/**
	 * @throws Exception
	 */
	public void productDao() throws Exception{
		BaseXmlElement daoConfigs=cfg.getDaoElement();
		Assert.notNull(daoConfigs,"dao config in configuraton.xml must not be null!");
		DaoElement daoConfig=DaoElement.class.cast(daoConfigs);
		//初始化模板路径
		Manufacture manu=new Manufacture(cfg.getBasePackagePath()+cfg.getPackagePrefix(),cfg.getBasePackagePath()+cfg.getPackagePrefix()+cfg.getModuleName()+"/dao/GirlDao.java");
		manu.initConfiguration();
		Map<String,Object> models=new HashMap<String,Object>();
		models.put("packagePrefix", cfg.getPackagePrefix().replaceAll("\\\\", ".").replaceFirst(".", ""));
		models.put("moduleName", cfg.getModuleName().replaceAll("\\\\", "."));
		models.put("entityName", daoConfig.getEntityName());
		models.put("primaryKeyType", daoConfig.getPrimaryKeyType());
		models.put("daoName", daoConfig.getDaoName());
		models.put("remarks", daoConfig.getRemarks());
		models.put("date", GeneratorConstant.DATE);
		models.put("author", GeneratorConstant.AUTHOR);
		models.put("version", GeneratorConstant.VERSION);
		manu.processDaoFile(models, manu.getProductPath());
		LOGGER.warn("dao class generate success!");
	}
	
	/**
	 * @throws Exception
	 */
	public void productDto() throws Exception{
		BaseXmlElement dtoConfigs=cfg.getDtoElement();
		Assert.notNull(dtoConfigs,"dao config in configuraton.xml must not be null!");
		DtoElement dtoConfig=DtoElement.class.cast(dtoConfigs);
		//初始化模板路径
		Manufacture manu=new Manufacture(cfg.getBasePackagePath()+cfg.getPackagePrefix(),cfg.getBasePackagePath()+cfg.getPackagePrefix()+cfg.getModuleName()+"/dto/GirlDto.java");
		manu.initConfiguration();
		Map<String,Object> models=new HashMap<String,Object>();
		models.put("packagePrefix", cfg.getPackagePrefix().replaceAll("\\\\", ".").replaceFirst(".", ""));
		models.put("moduleName", cfg.getModuleName().replaceAll("\\\\", "."));
		models.put("dtoName", dtoConfig.getDtoName());
		models.put("hasDateColumn", dtoConfig.isHasDateColumn());
		models.put("hasBigDecimalColumn", dtoConfig.isHasBigDecimalColumn());
		models.put("hasListColumn", dtoConfig.isHasListColumn());
		models.put("hasSetColumn", dtoConfig.isHasSetColumn());
		models.put("remarks", dtoConfig.getRemarks());
		models.put("date", GeneratorConstant.DATE);
		models.put("author", GeneratorConstant.AUTHOR);
		models.put("version", GeneratorConstant.VERSION);
		models.put("properties", dtoConfig.getProperties());
		models.put("setParameterTypelist", dtoConfig.getSetParameterTypelist());
		models.put("listParameterTypelist", dtoConfig.getListParameterTypelist());
		manu.processDtoFile(models, manu.getProductPath());
		LOGGER.warn("dto class generate success!");
	}
	
	/**
	 * @throws Exception
	 */
	public void productService() throws Exception{
		BaseXmlElement serviceConfigs=cfg.getServiceElement();
		Assert.notNull(serviceConfigs,"dao config in configuraton.xml must not be null!");
		ServiceElement daoConfig=ServiceElement.class.cast(serviceConfigs);
		//初始化模板路径
		Manufacture manu=new Manufacture(cfg.getBasePackagePath()+cfg.getPackagePrefix(),cfg.getBasePackagePath()+cfg.getPackagePrefix()+cfg.getModuleName()+"/service/GirlService.java");
		manu.initConfiguration();
		Map<String,Object> models=new HashMap<String,Object>();
		models.put("packagePrefix", cfg.getPackagePrefix().replaceAll("\\\\", ".").replaceFirst(".", ""));
		models.put("moduleName", cfg.getModuleName().replaceAll("\\\\", "."));
		models.put("daoName", daoConfig.getDaoName());
		models.put("serviceName", daoConfig.getServiceName());
		models.put("dtoName", daoConfig.getDtoName());
		models.put("entityName", daoConfig.getEntityName());
		models.put("entityFullName", daoConfig.getEntityFullName());
		models.put("dtoFullName", daoConfig.getDtoFullName());
		models.put("daoFullName", daoConfig.getDaoFullName());
		models.put("searchDtoName", daoConfig.getSearchDtoName());
		models.put("searchDtoFullName", daoConfig.getSearchDtoFullName());
		models.put("searchMethodName", daoConfig.getSearchMethodName());
		models.put("numberKey", daoConfig.getNumberKey());
		models.put("keyGetMethod", daoConfig.getKeyGetMethod());
		models.put("keySetMethod", daoConfig.getKeySetMethod());
		models.put("saveMethodName", daoConfig.getSaveMethodName());
		models.put("deleteMethodName", daoConfig.getDeleteMethodName());
		models.put("findMethodName", daoConfig.getFindMethodName());
		models.put("remarks", daoConfig.getRemarks());
		models.put("date", GeneratorConstant.DATE);
		models.put("author", GeneratorConstant.AUTHOR);
		models.put("version", GeneratorConstant.VERSION);
		manu.processServiceFile(models, manu.getProductPath());
		LOGGER.warn("service class generate success!");
	}
	
	/**
	 * @throws Exception
	 */
	public void productAction() throws Exception{
		BaseXmlElement daoConfigs=cfg.getDaoElement();
		Assert.notNull(daoConfigs,"dao config in configuraton.xml must not be null!");
		DaoElement daoConfig=DaoElement.class.cast(daoConfigs);
		//初始化模板路径
		Manufacture manu=new Manufacture(cfg.getBasePackagePath()+cfg.getPackagePrefix(),cfg.getBasePackagePath()+cfg.getPackagePrefix()+cfg.getModuleName()+"/dao/GirlDao.java");
		manu.initConfiguration();
		Map<String,Object> models=new HashMap<String,Object>();
		models.put("packagePrefix", cfg.getPackagePrefix().replaceAll("\\\\", ".").replaceFirst(".", ""));
		models.put("moduleName", cfg.getModuleName().replaceAll("\\\\", "."));
		models.put("entityName", daoConfig.getEntityName());
		models.put("primaryKeyType", daoConfig.getPrimaryKeyType());
		models.put("daoName", daoConfig.getDaoName());
		models.put("remarks", daoConfig.getRemarks());
		models.put("date", GeneratorConstant.DATE);
		models.put("author", GeneratorConstant.AUTHOR);
		models.put("version", GeneratorConstant.VERSION);
		manu.processDaoFile(models, manu.getProductPath());
		LOGGER.warn("dao class generate success!");
	}
	
	public static void main(String[] args) throws Exception {
		GenerateProducts utility=new GenerateProducts();
		//generate dao class
		utility.productDao();
		//generate dto class
		utility.productDto();
		//generate service class
		utility.productService();
	}
}
