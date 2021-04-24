package com.funny.combo.camel.entity;

import java.io.Serializable;

/**
 * http资源
 *
 * @author xiaoka
 *
 */
public class CamelvHttp implements Serializable {

    /**
     *
     */
    private String dataId;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String url;

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

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }
}
