package com.funny.demo.camel.entity;

import com.funny.combo.core.base.BaseEntity;
import java.util.Date;

public class DocInfoEntity extends BaseEntity {
    private static final long serialVersionUID = 1617202436294L;

    /**
    * 文件夹id
    */
    private Long docId;

    /**
    * 用户id
    */
    private Long docUserId;

    /**
    * 文件夹名称
    */
    private String docName;

    /**
    * 文件夹简介
    */
    private String docBiref;

    /**
    * 文件夹创建时间
    */
    private Date docCreateTime;

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocUserId(Long docUserId) {
        this.docUserId = docUserId;
    }

    public Long getDocUserId() {
        return docUserId;
    }

    public void setDocName(String docName) {
        this.docName = docName == null ? null : docName.trim();
    }

    public String getDocName() {
        return docName;
    }

    public void setDocBiref(String docBiref) {
        this.docBiref = docBiref == null ? null : docBiref.trim();
    }

    public String getDocBiref() {
        return docBiref;
    }

    public void setDocCreateTime(Date docCreateTime) {
        this.docCreateTime = docCreateTime;
    }

    public Date getDocCreateTime() {
        return docCreateTime;
    }
}
