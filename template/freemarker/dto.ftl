<#include "copyright.ftl"/>
package ${packagePrefix}${moduleName}.dto;

<#if (hasDateColumn)>
import java.util.Date;
</#if>
<#if (hasBigDecimalColumn)>
import java.math.BigDecimal;
</#if>
<#if (hasSetColumn)>
import java.util.Set;
</#if>
<#if (hasListColumn)>
import java.util.List;
</#if>
<#list setParameterTypelist as type>
import ${type};
</#list>
<#list listParameterTypelist as type>
import ${type};
</#list>

/**
 * <p>实体类Dto</p>
 * <p>Entity: ${dtoName} - ${remarks}</p>
 * @Date ${date}
 * @author ${author}
 * @since ${version}
 */
public class ${dtoName} {

<#list properties as prop>
    private ${prop.propType} ${prop.propName};
</#list>

	public ${dtoName}(){
		super();
	}
	

<#list properties as prop>

    public ${prop.propType} ${prop.getMethodName}(){
        return this.${prop.propName};
    }
    public void ${prop.setMethodName}(${prop.propType} ${prop.propName}){
        this.${prop.propName} = ${prop.propName};
    }
</#list>

}