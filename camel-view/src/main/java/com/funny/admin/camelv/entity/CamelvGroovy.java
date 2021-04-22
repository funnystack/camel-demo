package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

/**
 * groovy脚本资源
 * 
 * @author xiaoka
 *
 */
public class CamelvGroovy extends BaseEntity {
	private static final long serialVersionUID = -4178241291940091481L;
	private String name;// 名称
	private String script;// 脚本

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	@Override
	public String toString() {
		return "Groovy [name=" + name + ", script=" + script + "]";
	}
}
