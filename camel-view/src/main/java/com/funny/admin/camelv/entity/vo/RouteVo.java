package com.funny.admin.camelv.entity.vo;

import com.funny.combo.core.base.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * 非持久化的其他属性
 * 
 * @author xiaoka
 *
 */
public class RouteVo extends BaseEntity {

	private static final long serialVersionUID = 3834251997613098512L;

	/** 记录该路由后续需要执行的多个路由id */
	private List<String> to;
	/** 记录指向该路由的路由id */
	private List<String> from;
	/** 记录分支路由指向下一个路由的条件，key:当前路由指向下一个路由的条件，value：满足条件执行的下一个路由id */
	private Map<String, String> condition;
	/** 记录是否是聚合路由 */
	private Boolean aggregat;
	/** 关联的异常路由id */
	private String exceptionId;
	/** 参数 */
	private List<ComponentOption> optionList;

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getFrom() {
		return from;
	}

	public void setFrom(List<String> from) {
		this.from = from;
	}

	public Map<String, String> getCondition() {
		return condition;
	}
	
	public void setCondition(Map<String, String> condition) {
		this.condition = condition;
	}
	
	public String getExceptionId() {
		return exceptionId;
	}

	public void setExceptionId(String exceptionId) {
		this.exceptionId = exceptionId;
	}

	public Boolean getAggregat() {
		return aggregat;
	}

	public void setAggregat(Boolean aggregat) {
		this.aggregat = aggregat;
	}

	public List<ComponentOption> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<ComponentOption> optionList) {
		this.optionList = optionList;
	}
}
