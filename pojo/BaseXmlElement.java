/*
 * Copyright 2017 gp15237125756@126.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ld.common.generator.pojo;
/**
 * configuration template element description
 * @author Cruz
 * @version 01-00
 */
public abstract class BaseXmlElement {

    protected String templateId;
    protected String templateName;
    protected String engine;
    protected String templateFile;
    protected String targetPath;
    protected String targetFileName;
    protected String encoding;
    //date
	protected String date;
	//author
	protected String author;
	//version
	protected String version;

    
    public BaseXmlElement() {
		super();
	}

    public BaseXmlElement(String templateId, String templateName,
			String engine, String templateFile, String targetPath,
			String targetFileName, String encoding, String entityName,
			String primaryKeyType, String daoName, String remarks) {
		super();
		this.templateId = templateId;
		this.templateName = templateName;
		this.engine = engine;
		this.templateFile = templateFile;
		this.targetPath = targetPath;
		this.targetFileName = targetFileName;
		this.encoding = encoding;

	}



	public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    public String getTemplateFile() {
        return templateFile==null?"":templateFile;
    }

    public String getTargetPath() {
        return targetPath==null?"":targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public String getTargetFileName() {
        return targetFileName==null?"":targetFileName;
    }

    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }

    public String getTemplateName() {
        return templateName==null?"":templateName;
    }


    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    



    @Override
	public String toString() {
		return "TemplateElement [templateId=" + templateId + ", templateName="
				+ templateName + ", engine=" + engine + ", templateFile="
				+ templateFile + ", targetPath=" + targetPath
				+ ", targetFileName=" + targetFileName + ", encoding="
				+ encoding + ", date=" + date + ", author=" + author
				+ ", version=" + version + "]";
	}

	public String getEngine() {
        return engine==null?"freemarker":engine;
    }


    public void setEngine(String engine) {
        this.engine = engine;
    }


    public String getEncoding() {
        return encoding==null?"":encoding;
    }


    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getTemplateId() {
        return templateId==null?"":templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
    
}
