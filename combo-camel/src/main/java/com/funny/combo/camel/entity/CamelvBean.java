package com.funny.combo.camel.entity;

import java.io.Serializable;
import java.util.List;

/**
 * bean资源
 *
 * @author fangli
 *
 */
public class CamelvBean implements Serializable {

    /**
     *
     */
    private String dataId;

    /**
     *
     */
    private String beanName;

    /**
     *
     */
    private String methodName;
    /**
     * 参数
     */
    private List<ComponentOption> optionList;

    /**
     * 备注信息
     */
    private String remarks;

    public List<ComponentOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<ComponentOption> optionList) {
        this.optionList = optionList;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
    }

    public String getDataId() {
        return dataId;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
