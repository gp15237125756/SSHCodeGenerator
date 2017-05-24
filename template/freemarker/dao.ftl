<#include "copyright.ftl"/>
package ${packagePrefix}${moduleName}.dao;
import com.ld.common.repository.CustomRepository;
import ${packagePrefix}${moduleName}.entity.${entityName};
/**
 * <p>实体类Dto</p>
 * <p>Entity: ${entityName} - ${remarks}</p>
 * @Date ${date}
 * @author ${author}
 * @since ${version}
 */
public interface ${daoName}  extends CustomRepository<${entityName}, ${primaryKeyType}>{

}