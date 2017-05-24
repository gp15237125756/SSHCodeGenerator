package com.ld.common.generator.product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * manufacture code
 * @author Cruz
 * @version 01-00
 */
public class Manufacture {
	
	 private static final Log LOGGER  = LogFactory.getLog(Manufacture.class);

	    private static final String DEFAULT_ENCODING = "UTF-8";

	    private Configuration       config;

	    private String      classPath;
	    
	    private String productPath;

	    public Manufacture(String classPath,String productPath){
	        this.classPath = classPath;
	        this.productPath=productPath;
	        initConfiguration();
	    }

	    public void initConfiguration() {
	        try {
	            config = new Configuration(Configuration.VERSION_2_3_0);
	            config.setTemplateLoader(new ClassTemplateLoader(Thread.currentThread().getContextClassLoader(),classPath ));
	            config.setDirectoryForTemplateLoading(new File(classPath + "/common/generator/template/freemarker"));
	            config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	            config.setLogTemplateExceptions(false);
	            config.setSetting("classic_compatible", "true");
	            config.setSetting("whitespace_stripping", "true");
	            config.setSetting("template_update_delay", "1");
	            config.setSetting("locale", "zh_CN");
	            config.setSetting("default_encoding", DEFAULT_ENCODING);
	            config.setSetting("url_escaping_charset", DEFAULT_ENCODING);
	            config.setSetting("datetime_format", "yyyy-MM-dd hh:mm:ss");
	            config.setSetting("date_format", "yyyy-MM-dd");
	            config.setSetting("time_format", "HH:mm:ss");
	            config.setSetting("number_format", "0.######;");
	        } catch (Exception e) {
	            LOGGER.warn(e.getMessage(), e);
	        }
	    }

	  

	    /**
	     * generate dao interface
	     * @param model
	     * @param productPath
	     */
	    public void processDaoFile(Map<String, Object> model,String productPath)  {
	        try {
	            Template template = config.getTemplate("dao.ftl");
	            File file = new File(productPath);
	            if(!file.exists()){
	            	file.createNewFile();
	            }
	            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
	            template.process(model, out);
	            out.flush();
	            out.close();
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage(), e);
	        }
	    }
	    
	    /**
	     * generate dao interface
	     * @param model
	     * @param productPath
	     */
	    public void processActionFile(Map<String, Object> model,String productPath)  {
	        try {
	            Template template = config.getTemplate("action.ftl");
	            File file = new File(productPath);
	            if(!file.exists()){
	            	file.createNewFile();
	            }
	            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
	            template.process(model, out);
	            out.flush();
	            out.close();
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage(), e);
	        }
	    }
	    
	    /**
	     * generate dao interface
	     * @param model
	     * @param productPath
	     */
	    public void processServiceFile(Map<String, Object> model,String productPath)  {
	        try {
	            Template template = config.getTemplate("service.ftl");
	            File file = new File(productPath);
	            if(!file.exists()){
	            	file.createNewFile();
	            }
	            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
	            template.process(model, out);
	            out.flush();
	            out.close();
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage(), e);
	        }
	    }
	    

	    /**
	     * generate dao interface
	     * @param model
	     * @param productPath
	     */
	    public void processDtoFile(Map<String, Object> model,String productPath)  {
	        try {
	            Template template = config.getTemplate("dto.ftl");
	            File file = new File(productPath);
	            if(!file.exists()){
	            	file.createNewFile();
	            }
	            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
	            template.process(model, out);
	            out.flush();
	            out.close();
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage(), e);
	        }
	    }


		public String getClassPath() {
			return classPath;
		}

		public void setClassPath(String classPath) {
			this.classPath = classPath;
		}

		public String getProductPath() {
			return productPath;
		}

		public void setProductPath(String productPath) {
			this.productPath = productPath;
		}
	    
	    
	    
}
