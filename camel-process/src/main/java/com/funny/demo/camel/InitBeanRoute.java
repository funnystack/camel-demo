package com.funny.demo.camel;

import com.funny.combo.camel.context.CamelvContext;
import com.funny.combo.camel.entity.CamelvBean;
import com.funny.combo.camel.entity.CamelvLine;

public class InitBeanRoute {


    public static void init(){
        CamelvBean camelvBean1 = new CamelvBean();
        camelvBean1.setDataId("bean1");
        camelvBean1.setBeanName("reduceService");
        camelvBean1.setMethodName("reduceStore");
        CamelvContext.addCamelvBean(camelvBean1);

        CamelvBean camelvBean2 = new CamelvBean();
        camelvBean2.setDataId("bean2");
        camelvBean2.setBeanName("rollBackService");
        camelvBean2.setMethodName("rollBackPoints");
        CamelvContext.addCamelvBean(camelvBean2);

        CamelvLine camelvLine = new CamelvLine();
        camelvLine.setLineId("line1");
        camelvLine.setFlowId("testflow");
        camelvLine.setName("line Name");
        camelvLine.setFromRouteId("bean1");
        camelvLine.setToRouteId("bean2");

        CamelvContext.addCamelvRouteLine(camelvLine);
    }
}
