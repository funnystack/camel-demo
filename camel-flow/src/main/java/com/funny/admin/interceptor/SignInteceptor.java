package com.funny.admin.interceptor;

import com.alibaba.fastjson.JSON;
import com.funny.admin.common.sn.APIException;
import com.funny.admin.common.sn.SignUtil;
import com.funny.admin.common.sn.WithSign;
import com.funny.combo.core.result.CommonResult;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 签名拦截器
 *
 * @author fangli
 * @desc
 * @date 2017/5/9 下午2:54
 */
@Component
public class SignInteceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SignInteceptor.class);
    private Map<String, String> appMap;

    public void setAppMap(Map<String, String> appMap) {
        this.appMap = appMap;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        CommonResult<Object> ret = CommonResult.fail("");
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        WithSign method = handlerMethod.getMethodAnnotation(WithSign.class);
        if (method == null) {
            Class<?> clazz = handlerMethod.getBeanType();
            method = clazz.getAnnotation(WithSign.class);
        }
        if (method == null) {
            return true;
        }
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> params = Maps.newHashMap();
        for (String key : map.keySet()) {
            String[] vals = map.get(key);
            if (vals != null && vals.length != 0) {
                String temp = "";
                for (String val : vals) {
                    temp += val;
                }
                if (!Strings.isNullOrEmpty(temp)) {
                    params.put(key, temp);
                }
            }
        }
        String appId = request.getParameter("_appid");
        if (appId == null || appId.isEmpty()) {
            ret.setMessage("缺少_appid");
            sendJSON(response, ret);
            return false;
        }

        if (!appMap.containsKey(appId.toLowerCase())) {
            ret = CommonResult.fail("无效的_appid");
            sendJSON(response, ret);
            return false;
        }
        String appkey = request.getParameter("_appkey");
        if (appkey == null || appkey.isEmpty()) {
            ret = CommonResult.fail("缺少appkey");
            sendJSON(response, ret);
            return false;
        }

        try {
            SignUtil.verifySign(appId, appkey, params);
            return true;
        } catch (APIException e) {
            ret.setCode(e.getErrorCode());
            ret.setMessage(e.getReturnMessage());
            sendJSON(response, ret);
            logger.error("签名验证异常,url={},params={}", request.getRequestURI(), JSON.toJSONString(params), e);
            return false;
        }
    }


    protected boolean sendJSON(HttpServletResponse response, Object obj) {
        response.setContentType("text/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        StringBuffer sb = new StringBuffer();
        try {
            PrintWriter writer = response.getWriter();
            sb.append(JSON.toJSONString(obj));
            writer.write(sb.toString());
            response.flushBuffer();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
