/*******************************************************************************
 * ExchangeGiftManageAction.java 01-00
 *
 * Copyright 2014 by LD, Ltd. All right reserved.
 *
 *	2017/01/22 01-00 xxt
 *******************************************************************************/
 <#include "copyright.ftl"/>
package ${packagePrefix}${moduleName}.action;
import ${packagePrefix}${moduleName}.entity.${entityName};
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import com.ld.audience.dto.memberLevel.MemberArticleDto;
import com.ld.audience.dto.memberLevel.search.MemberArticleSearchDto;
import com.ld.audience.service.MemberArticleService;
import com.ld.basic.entity.CodeType;
import com.ld.common.exception.ExclusiveException;
import com.ld.common.web.BasicActionSupport;

/**
 * 兑换物品管理Action
 *
 * [init] 画面初期表示
 *
 * 
 *
 * @version 01-00
 */
@Controller
@Scope("prototype")
public class ${actionName} extends BasicActionSupport {

	/** 序列ID */
	private static final long serialVersionUID = 2020103147209065573L;

	private MemberArticleDto memberArticleDto;
	
	@Autowired
	private MemberArticleService memberArticleService;
	//积分比例类型共通
	private List<CodeType> memberArticleUnitTypes;
	//search dto
	private MemberArticleSearchDto memberArticleSearchDto;
	//search result
	private Page<MemberArticleDto> memberArticleDtos;
	/**
	 * 画面初期表示
	 * @return String
	 * @since 01-00
	 */
	public String init() {
		// 权限验证
		// 验证出错设置出错信息
		// 验证出错返回error画面

		return SUCCESS;
	}
	
	/**
	 * 兑换物品信息查询
	 * @return String
	 * @since 01-00
	 */
	public String exchangeGiftSearch() {
		// 权限验证
		// 验证出错设置出错信息
		// 验证出错返回error画面
		memberArticleDtos=memberArticleService.searchExchangeArticles(memberArticleSearchDto,this.pb);
		return SUCCESS;
	}
	
	/**
	 * 兑换物品登记画面初期化
	 * @return String
	 * @since 01-00
	 */
	public String exchangeGiftRegisterInit() {
		// 权限验证
		// 验证出错设置出错信息
		// 验证出错返回error画面
		memberArticleUnitTypes=memberArticleService.getArticleQuantityUnitTypes();
		return SUCCESS;
	}
	
	/**
	 * @return String
	 * @since 01-00
	 */
	@SuppressWarnings("unchecked")
	public String exchangeGiftDelete() {
		// 权限验证
		// 验证出错设置出错信息
		// 验证出错返回error画面
		if(memberArticleDto==null){
			return error400();
		}
		//default no exception
		String msg="兑换物品删除成功！"; 
		try{
			memberArticleDto=memberArticleService.deleteMemberArticle(memberArticleDto);
			resultJson.setResult(true);
		}catch(ExclusiveException exclusive){
			exclusive.printStackTrace();
			msg="请求的资源已经被他人修改，请重新查询！"; 
		}catch(Exception ex){
			ex.printStackTrace();
			msg="兑换物品删除失败，请联系管理员！"; 
		}
		resultJson.setMessage(msg);
		return SUCCESS;
	}
	
	/**
	 * @return String
	 * @since 01-00
	 */
	@SuppressWarnings("unchecked")
	public String exchangeGiftEdit() {
		if(memberArticleDto==null){
			return error400();
		}
		memberArticleUnitTypes=memberArticleService.getArticleQuantityUnitTypes();
		memberArticleDto=memberArticleService.findMemberArticle(memberArticleDto);
		resultJson.setResult(true);
		resultJson.setData(memberArticleDto);
		return SUCCESS;
	}
	
	/**
	 * @return String
	 * @since 01-00
	 */
	@SuppressWarnings("unchecked")
	public String exchangeGiftShow() {
		if(memberArticleDto==null){
			return error400();
		}
		memberArticleUnitTypes=memberArticleService.getArticleQuantityUnitTypes();
		memberArticleDto=memberArticleService.findMemberArticle(memberArticleDto);
		resultJson.setResult(true);
		resultJson.setData(memberArticleDto);
		return SUCCESS;
	}

	/**
	 * @return String
	 * @since 01-00
	 */
	@SuppressWarnings("unchecked")
	public String exchangeGiftSave() {
		// 权限验证
		// 验证出错设置出错信息
		// 验证出错返回error画面
		if(memberArticleDto==null){
			return error400();
		}
		//default no exception
		String hintMsg=null,typeMsg=null;
		if(memberArticleDto.getArticleId()==null){
			typeMsg="保存";
		}else{
			typeMsg="修改";
		}
		try{
			memberArticleService.saveExchangeArticle(memberArticleDto);
			resultJson.setResult(true);
			hintMsg="兑换物品"+typeMsg+"成功！";
		}catch(ExclusiveException exclusive){
			exclusive.printStackTrace();
			hintMsg="请求的资源已经被他人修改，请重新查询！"; 
		}catch(Exception ex){
			ex.printStackTrace();
			hintMsg="兑换物品"+typeMsg+"失败，请联系管理员！"; 
		}
		resultJson.setMessage(hintMsg);
		return SUCCESS;
	}

	public MemberArticleDto getMemberArticleDto() {
		return memberArticleDto;
	}

	public void setMemberArticleDto(MemberArticleDto memberArticleDto) {
		this.memberArticleDto = memberArticleDto;
	}

	public List<CodeType> getMemberArticleUnitTypes() {
		return memberArticleUnitTypes;
	}

	public void setMemberArticleUnitTypes(List<CodeType> memberArticleUnitTypes) {
		this.memberArticleUnitTypes = memberArticleUnitTypes;
	}

	public MemberArticleSearchDto getMemberArticleSearchDto() {
		return memberArticleSearchDto;
	}

	public void setMemberArticleSearchDto(
			MemberArticleSearchDto memberArticleSearchDto) {
		this.memberArticleSearchDto = memberArticleSearchDto;
	}

	public Page<MemberArticleDto> getMemberArticleDtos() {
		return memberArticleDtos;
	}

	public void setMemberArticleDtos(Page<MemberArticleDto> memberArticleDtos) {
		this.memberArticleDtos = memberArticleDtos;
	}
	
	
}