<#include "copyright.ftl"/>
package ${packagePrefix}${moduleName}.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ld.audience.AudienceConstants;
import ${daoFullName};
import ${dtoFullName};
import ${searchDtoFullName};
import ${entityFullName};
import com.ld.basic.BasicConstants;
import com.ld.basic.dao.CodeTypeDao;
import com.ld.basic.entity.CodeType;
import com.ld.basic.entity.UserInfo;
import com.ld.basic.service.CodeService;
import com.ld.basic.service.NumberMasterService;
import com.ld.common.components.PageBean;
import com.ld.common.exception.ExclusiveException;
import com.ld.common.exception.ResourceNotFoundException;
import com.ld.common.utils.BeanUtils;
import com.ld.common.utils.LDSecurityUtils;
import com.ld.common.utils.SearchUtils;

/**
 * 服务类-${serviceName}
 * @Date ${date}
 * @author ${author}
 * @since ${version}
 */
@Component
@Transactional(rollbackFor = java.lang.Exception.class)
public class ${serviceName} {
	@Autowired
	private CodeService codeService;
	@Autowired
	private CodeTypeDao codeTypeDao;
	@Autowired
	private ${daoName} ${daoName};
	@Autowired
	private NumberMasterService numberMasterService;
	
	/**
	 * get Point ProportionType
	 * @return {@code List<CodeType>}
	 */
	public  List<CodeType>  getArticleQuantityUnitTypes(){
		Optional<List<CodeType>> opt= Optional.of(codeService.getLargeCodeTypes(BasicConstants.TRADITION_UNIT));
		if(opt.isPresent()){
			return opt.get();
		}else{
			return new ArrayList<CodeType>();
		}
	}
	
	/**
	 * @param ${searchDtoName}
	 * @return List<${dtoName}>
	 */
	public Page<${dtoName}> ${searchMethodName}(${searchDtoName} ${searchDtoName},PageBean pb){
		List<${dtoName}> ret=new LinkedList<${dtoName}>();
		// 定义sf-设定查询条件以及查询结果类型
		Specification<${entityName}> sf = SearchUtils.buildSpecification(${searchDtoName},
				${entityName}.class);
		PageRequest pageRequest = SearchUtils.buildPageRequest(pb);
		Page<${entityName}> resultPage=${daoName}.findAll(sf,pageRequest);
		// resultPage为null时，直接返回
		if (null == resultPage) {
			return new PageImpl<${dtoName}>(
					new ArrayList<${dtoName}>(), pageRequest, 0);
		}
		while(resultPage!=null&&resultPage.getContent().size()==0){
			if(resultPage.isFirst()){
				return new PageImpl<${dtoName}>(
						new ArrayList<${dtoName}>(), pageRequest, 0);
			}else{
				pb.setPageNumber(pb.getPageNumber()-1);
				pageRequest = SearchUtils.buildPageRequest(pb);
				resultPage = ${daoName}.findAll(sf, pageRequest);
			}
		}
		List<${entityName}> contents=resultPage.getContent();
		contents.forEach((m)->{
			${dtoName} dto=new ${dtoName}();
			BeanUtils.objectCopy(m, dto);
			ret.add(dto);
		});
		return  new PageImpl<${dtoName}>(ret,pageRequest,resultPage.getTotalElements());
	}
	
	
	/**
	 * @param ${dtoName}
	 */
	public void ${saveMethodName}(${dtoName} ${dtoName}){
		UserInfo user=LDSecurityUtils.getCurrentUser();
		Long primaryId=${dtoName}.${keyGetMethod}();
		//add
		if(primaryId==null){
			${entityName} entity=new ${entityName}();
			BeanUtils.objectCopy(${dtoName}, entity);
			<#if (numberKey)>
			entity.${keySetMethod}(Long.parseLong(numberMasterService.getNumber(AudienceConstants.MEMBER_EXCHANGE_ARTICLE, user).toString()));
			</#if>
			entity.setDelFlag(BasicConstants.DEL_FLAG_F);
			entity.setCreateDateTime(new Date());
			entity.setCreateUserId(user.getUserId());
			entity.setUpdateDateTime(new Date());
			entity.setUpdateUserId(user.getUserId());
			${daoName}.save(entity);
		}else{
		//mend
			//update
			${entityName} previousLevel=${daoName}.findOne(primaryId);
			//**	exclude handle start*//*
			if(null==previousLevel){
				throw new ExclusiveException();
			}
			if(!${daoName}.isExclusive(${dtoName}.getUpdateDateTime(), previousLevel)){
				throw new ExclusiveException();
			}
			
			//previousLevel.setArticleName(memberArticleDto.getArticleName());
			//previousLevel.setArticleQuantity(memberArticleDto.getArticleQuantity());
			//previousLevel.setQuantityUnit(memberArticleDto.getQuantityUnit());
			//previousLevel.setPoint(memberArticleDto.getPoint());
			//add required attributes here
			//TODO 
			previousLevel.setUpdateDateTime(new Date());
			previousLevel.setUpdateUserId(user.getUserId());
			${daoName}.save(previousLevel);
		}
	}
	
	
	
	/**
	 * delete
	 * @param ${dtoName}
	 */
	public  ${dtoName}  ${deleteMethodName}(${dtoName} ${dtoName}){
		UserInfo user=LDSecurityUtils.getCurrentUser();
		${entityName} entity=${daoName}.findOne(${dtoName}.${keyGetMethod}());
		if(null==entity){
			throw new ExclusiveException();
		}
		if(!${daoName}.isExclusive(${dtoName}.getUpdateDateTime(), entity)){
			throw new ExclusiveException();
		}
		entity.setUpdateUserId(user.getUserId());
		entity.setUpdateDateTime(new Date());
		entity.setDelFlag(true);
		${entityName} persisted=${daoName}.save(entity);
		${dtoName} resultDto=new ${dtoName}();
		BeanUtils.objectCopy(persisted, resultDto);
		return resultDto;
	}
	
	/**
	 * get member level info
	 * @param ${dtoName}
	 */
	public ${dtoName} ${findMethodName}(${dtoName} ${dtoName}){
		${dtoName} result=new ${dtoName}();
		${entityName} entity=${daoName}.findOne(${dtoName}.${keyGetMethod}());
		if(entity==null){
			throw new ResourceNotFoundException();
		}
		BeanUtils.objectCopy(entity, result);
		return result;
	}

	
}
