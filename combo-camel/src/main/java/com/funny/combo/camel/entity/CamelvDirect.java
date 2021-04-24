package com.funny.combo.camel.entity;

import java.io.Serializable;

/**
 * direct资源
 *
 * @author xiaoka
 *
 */
public class CamelvDirect implements Serializable {

    /**
     *
     */
    private String dataId;

    /**
     *
     */
    private String name;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
