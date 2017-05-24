<#include "copyright.ftl"/>
package ${packagePrefix}${moduleName}.dto.search;

import com.ld.basic.BasicConstants;
/**
 * <p>实体类Search Dto</p>
 * <p>Entity: ${dtoName} - ${remarks}</p>
 * @Date ${date}
 * @author ${author}
 * @since ${version}
 */
public class TicketPriceSearchDto {
	/**票务id*/
	private Long search_EQ_priceId;
	/**	删除标记*/
	private Boolean search_EQ_delFlag=BasicConstants.DEL_FLAG_F;
	/**	全票code*/
	private String search_EQ_fullTicketCode;
	/**	半票code*/
	private String search_EQ_halfTicketCode;
	/**	团体票code*/
	private String search_EQ_groupTicketCode;

	public TicketPriceSearchDto() {
	}

	public Long getSearch_EQ_priceId() {
		return search_EQ_priceId;
	}

	public void setSearch_EQ_priceId(Long search_EQ_priceId) {
		this.search_EQ_priceId = search_EQ_priceId;
	}


	public Boolean getSearch_EQ_delFlag() {
		return search_EQ_delFlag;
	}

	public void setSearch_EQ_delFlag(Boolean search_EQ_delFlag) {
		this.search_EQ_delFlag = search_EQ_delFlag;
	}

	public String getSearch_EQ_fullTicketCode() {
		return search_EQ_fullTicketCode;
	}

	public void setSearch_EQ_fullTicketCode(String search_EQ_fullTicketCode) {
		this.search_EQ_fullTicketCode = search_EQ_fullTicketCode;
	}

	public String getSearch_EQ_halfTicketCode() {
		return search_EQ_halfTicketCode;
	}

	public void setSearch_EQ_halfTicketCode(String search_EQ_halfTicketCode) {
		this.search_EQ_halfTicketCode = search_EQ_halfTicketCode;
	}

	public String getSearch_EQ_groupTicketCode() {
		return search_EQ_groupTicketCode;
	}

	public void setSearch_EQ_groupTicketCode(String search_EQ_groupTicketCode) {
		this.search_EQ_groupTicketCode = search_EQ_groupTicketCode;
	}
	



}