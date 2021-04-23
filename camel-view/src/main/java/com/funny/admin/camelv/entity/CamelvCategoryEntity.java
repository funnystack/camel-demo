package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

public class CamelvCategoryEntity extends BaseEntity {
    private static final long serialVersionUID = 1619158015447L;

    /**
    * 
    */
    private String dataId;

    /**
    * 类别名
    */
    private String name;

    /**
    * 父类别id
    */
    private String pId;

    /**
    * 备注信息
    */
    private String remarks;

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

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String getpId() {
        return pId;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }
}