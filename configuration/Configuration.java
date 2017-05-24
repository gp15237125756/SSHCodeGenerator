package com.ld.common.generator.configuration;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ld.common.generator.GeneratorConstant;
import com.ld.common.generator.pojo.BaseXmlElement;
import com.ld.common.generator.pojo.ColumnProperty;
import com.ld.common.generator.pojo.DaoElement;
import com.ld.common.generator.pojo.DtoElement;
import com.ld.common.generator.pojo.ServiceElement;

import freemarker.template.utility.ClassUtil;

/**
 * read class configuration 
 * @author Cruz
 * @Date 2017-5-19
 * @version 01-00
 */
public class Configuration {
	
	private static final Log LOGGER=LogFactory.getLog(Configuration.class);
	//cfg configuration file path
	private String cfgFileName=GeneratorConstant.CFG_FILE_NAME;
	//包文件路径
	private String basePackagePath;
	//模块前缀
	private String packagePrefix;
	//cfg real path
	private String cfgPath;
	//模块名称
	private String moduleName;
	//template list
	private List<BaseXmlElement> elements=new LinkedList<BaseXmlElement>();
	
	public Configuration(){
		cfgPath=GeneratorConstant.CFG_PATH+cfgFileName;
	}
	//load configuration.xml
	public void loadConfiguration() throws Exception{
		 LOGGER.warn("start loading configuration.xml!");
		 DocumentBuilderFactory domfac=DocumentBuilderFactory.newInstance();
	     DocumentBuilder domBuilder = domfac.newDocumentBuilder();   
         InputStream is = new FileInputStream(new File(this.cfgPath));   
         Document doc = domBuilder.parse(is);  
         XPathFactory f = XPathFactory.newInstance();
         XPath path = f.newXPath();
         basePackagePath = path.evaluate("configuration/basePackagePath/text()", doc);
         packagePrefix = path.evaluate("configuration/packagePrefix/text()", doc);
         moduleName = path.evaluate("configuration/moduleName/text()", doc);
         NodeList NodeLists = (NodeList)path.evaluate("configuration/templates/template", doc,XPathConstants.NODESET);
         Assert.notNull(NodeLists);
		 for(int i=0;i<NodeLists.getLength();++i){
			 Node node=NodeLists.item(i);
			// path.evaluate("./templateFile/text()", node);
			 String type=path.evaluate("@id", node);
			 if(StringUtils.equals(GeneratorConstant.templateType.DAO.getId(),type)){
				 DaoElement ele$=new DaoElement();
				 ele$.setTemplateId(path.evaluate("@id", node));
				 ele$.setEngine(path.evaluate("@engine", node));
				 ele$.setTemplateName(path.evaluate("@name", node));
				 ele$.setTemplateId(type);
				 processDaoAnnotation(ele$,node,path);
			 }else if(StringUtils.equals(GeneratorConstant.templateType.DTO.getId(),type)){
				 DtoElement ele$=new DtoElement();
				 ele$.setTemplateId(path.evaluate("@id", node));
				 ele$.setEngine(path.evaluate("@engine", node));
				 ele$.setTemplateName(path.evaluate("@name", node));
				 ele$.setTemplateId(type);
				 processDtoAnnotation(ele$,node,path);
			 }else if(StringUtils.equals(GeneratorConstant.templateType.ACTION.getId(),type)){
				 processActionAnnotation();
			 }else if(StringUtils.equals(GeneratorConstant.templateType.SERVICE.getId(),type)){
				 ServiceElement ele$=new ServiceElement();
				 ele$.setTemplateId(path.evaluate("@id", node));
				 ele$.setEngine(path.evaluate("@engine", node));
				 ele$.setTemplateName(path.evaluate("@name", node));
				 ele$.setTemplateId(type);
				 processServiceAnnotation(ele$,node,path);
			 }
			
			
		 }
		 LOGGER.warn("complete loading configuration.xml!");
	}
	
	public void processDaoAnnotation(DaoElement ele$, Node node,XPath path) throws XPathExpressionException{
		 Node first= node.getFirstChild();
		 do{
			 if(StringUtils.equals("templateFile", first.getNodeName())){
				 ele$.setTemplateFile(path.evaluate("./templateFile/text()", node));
			 }else if(StringUtils.equals("entityName", first.getNodeName())){
				 ele$.setEntityName(path.evaluate("./entityName/text()", node));
			 }
			 else if(StringUtils.equals("primaryKeyType", first.getNodeName())){
				 ele$.setPrimaryKeyType(path.evaluate("./primaryKeyType/text()", node));
			 }
			 else if(StringUtils.equals("daoName", first.getNodeName())){
				 ele$.setDaoName(path.evaluate("./daoName/text()", node));
			 }
			 else if(StringUtils.equals("remarks", first.getNodeName())){
				 ele$.setRemarks(path.evaluate("./remarks/text()", node));
			 }
		 }while((first=first.getNextSibling())!=null);
		 elements.add(ele$);
	}
	
	/**
	 * @param ele$
	 * @param node
	 * @param path
	 * @throws XPathExpressionException
	 * @throws ClassNotFoundException
	 * @throws IntrospectionException
	 */
	public void processDtoAnnotation(DtoElement ele$, Node node,XPath path) throws XPathExpressionException, ClassNotFoundException, IntrospectionException{
		Node first= node.getFirstChild();
		 do{
			 if(StringUtils.equals("dtoName", first.getNodeName())){
				 ele$.setDtoName(path.evaluate("./dtoName/text()", node));
			 }else if(StringUtils.equals("entityFullName", first.getNodeName())){
				String entityFullName=path.evaluate("./entityFullName/text()", node);
				Assert.notNull(entityFullName, "entity full name must not be null!");
				extractDtoProperties(entityFullName,ele$);
			 }
			 else if(StringUtils.equals("remarks", first.getNodeName())){
				 ele$.setRemarks(path.evaluate("./remarks/text()", node));
			 }
		 }while((first=first.getNextSibling())!=null);
		 elements.add(ele$);
	}
	
	public void processActionAnnotation(){
		
	}
	
	public void processServiceAnnotation(ServiceElement ele$, Node node,XPath path) throws XPathExpressionException{
		 Node first= node.getFirstChild();
		 do{
			 if(StringUtils.equals("daoName", first.getNodeName())){
				 ele$.setDaoName(path.evaluate("./daoName/text()", node));
			 }else if(StringUtils.equals("serviceName", first.getNodeName())){
				 ele$.setServiceName(path.evaluate("./serviceName/text()", node));
			 }
			 else if(StringUtils.equals("dtoName", first.getNodeName())){
				 ele$.setDtoName(path.evaluate("./dtoName/text()", node));
			 }
			 else if(StringUtils.equals("entityName", first.getNodeName())){
				 ele$.setEntityName(path.evaluate("./entityName/text()", node));
			 }
			 else if(StringUtils.equals("searchDtoName", first.getNodeName())){
				 ele$.setSearchDtoName(path.evaluate("./searchDtoName/text()", node));
			 }
			 else if(StringUtils.equals("entityFullName", first.getNodeName())){
				 ele$.setEntityFullName(path.evaluate("./entityFullName/text()", node));
			 }
			 else if(StringUtils.equals("dtoFullName", first.getNodeName())){
				 ele$.setDtoFullName(path.evaluate("./dtoFullName/text()", node));
			 }
			 else if(StringUtils.equals("daoFullName", first.getNodeName())){
				 ele$.setDaoFullName(path.evaluate("./daoFullName/text()", node));
			 }
			 else if(StringUtils.equals("searchDtoFullName", first.getNodeName())){
				 ele$.setSearchDtoFullName(path.evaluate("./searchDtoFullName/text()", node));
			 }
			 else if(StringUtils.equals("searchMethodName", first.getNodeName())){
				 ele$.setSearchMethodName(path.evaluate("./searchMethodName/text()", node));
			 }
			 else if(StringUtils.equals("numberKey", first.getNodeName())){
				 ele$.setNumberKey(path.evaluate("./numberKey/text()", node));
			 }
			 else if(StringUtils.equals("keyGetMethod", first.getNodeName())){
				 ele$.setKeyGetMethod(path.evaluate("./keyGetMethod/text()", node));
			 }
			 else if(StringUtils.equals("keySetMethod", first.getNodeName())){
				 ele$.setKeySetMethod(path.evaluate("./keySetMethod/text()", node));
			 }
			 else if(StringUtils.equals("saveMethodName", first.getNodeName())){
				 ele$.setSaveMethodName(path.evaluate("./saveMethodName/text()", node));
			 }
			 else if(StringUtils.equals("deleteMethodName", first.getNodeName())){
				 ele$.setDeleteMethodName(path.evaluate("./deleteMethodName/text()", node));
			 }
			 else if(StringUtils.equals("findMethodName", first.getNodeName())){
				 ele$.setFindMethodName(path.evaluate("./findMethodName/text()", node));
			 }
			 else if(StringUtils.equals("remarks", first.getNodeName())){
				 ele$.setRemarks(path.evaluate("./remarks/text()", node));
			 }
		 }while((first=first.getNextSibling())!=null);
		 elements.add(ele$);
	}
	
	/**
	 * 抽象出所有get/set 属性
	 * @param entityFullName
	 * @param ele$
	 * @throws ClassNotFoundException 
	 * @throws IntrospectionException 
	 */
	public void extractDtoProperties(String entityFullName,DtoElement ele$) throws ClassNotFoundException, IntrospectionException{
		List<ColumnProperty> props=new LinkedList<ColumnProperty>();
		List<String> setParameterTypeFullNames=new LinkedList<String>();
		List<String> listParameterTypeFullNames=new LinkedList<String>();
		Class<?> entity=ClassUtil.forName(entityFullName);
		BeanInfo beanInfo=Introspector.getBeanInfo(entity);
		PropertyDescriptor[] descriptors=beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor desc:descriptors){
			if(desc.getPropertyType().isAssignableFrom(Class.class)){
				continue;
			}
			ColumnProperty col=new ColumnProperty();
			if("java.util.Set".equals(desc.getPropertyType().getName())){
				Method read=desc.getWriteMethod();
				Type type=read.getGenericParameterTypes()[0];
				if (type instanceof ParameterizedType) {
			        ParameterizedType pType = (ParameterizedType)type;
			        String pTypeName= pType.getActualTypeArguments()[0].getTypeName();
			        col.setPropType("Set<"+pTypeName.substring(pTypeName.lastIndexOf(".")+1)+">");
			        setParameterTypeFullNames.add(pTypeName);
			    } else {
			    	col.setPropType(desc.getPropertyType().getSimpleName());
			    }
			}else if("java.util.List".equals(desc.getPropertyType().getName())){
				Method read=desc.getWriteMethod();
				Type type=read.getGenericParameterTypes()[0];
				if (type instanceof ParameterizedType) {
			        ParameterizedType pType = (ParameterizedType)type;
			        String pTypeName= pType.getActualTypeArguments()[0].getTypeName();
			        col.setPropType("List<"+pTypeName.substring(pTypeName.lastIndexOf(".")+1)+">");
			        listParameterTypeFullNames.add(pTypeName);
			    } else {
			    	col.setPropType(desc.getPropertyType().getSimpleName());
			    }
			}else{
				col.setPropType(desc.getPropertyType().getSimpleName());
			}
			
			col.setPropName(desc.getName());
			col.setSetMethodName(desc.getWriteMethod().getName());
			col.setGetMethodName(desc.getReadMethod().getName());
			props.add(col);
		}
		ele$.setProperties(props);
		ele$.setSetParameterTypelist(setParameterTypeFullNames);
		ele$.setListParameterTypelist(listParameterTypeFullNames);
		ele$.setHasDateColumn(Stream.of(descriptors).anyMatch((d)-> "java.util.Date".equals(d.getPropertyType().getName())));
		ele$.setHasBigDecimalColumn(Stream.of(descriptors).anyMatch((d)-> "java.math.BigDecimal".equals(d.getPropertyType().getName())));
		ele$.setHasListColumn(Stream.of(descriptors).anyMatch((d)-> "java.util.List".equals(d.getPropertyType().getName())));
		ele$.setHasSetColumn(Stream.of(descriptors).anyMatch((d)-> "java.util.Set".equals(d.getPropertyType().getName())));
		
	}
	
	/**
	 * @return TemplateElement
	 */
	public BaseXmlElement getDaoElement(){
		Optional<BaseXmlElement> daoOpt=this.elements.stream().filter((o)->o.getTemplateId().equals(GeneratorConstant.templateType.DAO.getId())).findFirst();
		if(daoOpt.isPresent()){
			return daoOpt.get();
		}
		return null;
	}
	
	/**
	 * @return TemplateElement
	 */
	public BaseXmlElement getDtoElement(){
		Optional<BaseXmlElement> daoOpt=this.elements.stream().filter((o)->o.getTemplateId().equals(GeneratorConstant.templateType.DTO.getId())).findFirst();
		if(daoOpt.isPresent()){
			return daoOpt.get();
		}
		return null;
	}
	
	/**
	 * @return TemplateElement
	 */
	public BaseXmlElement getServiceElement(){
		Optional<BaseXmlElement> daoOpt=this.elements.stream().filter((o)->o.getTemplateId().equals(GeneratorConstant.templateType.SERVICE.getId())).findFirst();
		if(daoOpt.isPresent()){
			return daoOpt.get();
		}
		return null;
	}
	
	/**
	 * @return TemplateElement
	 */
	public BaseXmlElement getActionElement(){
		Optional<BaseXmlElement> daoOpt=this.elements.stream().filter((o)->o.getTemplateId().equals(GeneratorConstant.templateType.ACTION.getId())).findFirst();
		if(daoOpt.isPresent()){
			return daoOpt.get();
		}
		return null;
	}
	
	
	public String getCfgFileName() {
		return cfgFileName;
	}


	public void setCfgFileName(String cfgFileName) {
		this.cfgFileName = cfgFileName;
	}


	public String getCfgPath() {
		return cfgPath;
	}


	public void setCfgPath(String cfgPath) {
		this.cfgPath = cfgPath;
	}


	public String getBasePackagePath() {
		return basePackagePath;
	}
	public void setBasePackagePath(String basePackagePath) {
		this.basePackagePath = basePackagePath;
	}
	public String getPackagePrefix() {
		return packagePrefix;
	}
	public void setPackagePrefix(String packagePrefix) {
		this.packagePrefix = packagePrefix;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public List<BaseXmlElement> getElements() {
		return elements;
	}
	public void setElements(List<BaseXmlElement> elements) {
		this.elements = elements;
	}
	
	
	
}
