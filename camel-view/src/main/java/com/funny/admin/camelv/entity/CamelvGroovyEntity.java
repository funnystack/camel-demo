package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

public class CamelvGroovyEntity extends BaseEntity {
    private static final long serialVersionUID = 1619158042682L;

    /**
     *
     */
    private String dataId;

    /**
     *
     */
    private String name;

    /**
     * 备注信息
     */
    private String remarks;
    /**
     *
     */
    private String script;

    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
    }

    public String getDataId() {
        return dataId;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getName() {
        return name;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }


    public void setScript(String script) {
        this.script = script == null ? null : script.trim();
    }

    public String getScript() {
        return script;
    }
}