package com.funny.demo.camel.controller;

import com.alibaba.fastjson.JSON;
import com.funny.combo.core.result.SingleResponse;
import com.funny.demo.camel.entity.DocInfoEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *
 */
@RestController
public class DocController {


    @GetMapping("/queryDocInfoById")
    public SingleResponse queryDocInfoById(String id) {
        DocInfoEntity docInfoEntity = new DocInfoEntity();
        docInfoEntity.setDocId(Long.valueOf(id));
        docInfoEntity.setDocName("docname" + id);
        docInfoEntity.setDocUserId(123L);
        docInfoEntity.setDocBiref("社会科学");
        docInfoEntity.setDocCreateTime(new Date());
        SingleResponse singleResponse = SingleResponse.buildSuccess();
        singleResponse.setData(docInfoEntity);
        System.out.println("queryDocInfoById success,id="+id);
        return singleResponse;
    }


    @PostMapping("/updateDocInfo")
    public SingleResponse updateDocInfo(@RequestBody DocInfoEntity docInfoEntity) {
        System.out.println("updateDocInfo success,"+ JSON.toJSONString(docInfoEntity));
        return SingleResponse.buildSuccess();
    }
}
