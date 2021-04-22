package com.funny.admin.filter;

import com.funny.admin.common.utils.XSSShieldUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


/** 
 * @author  fangli
 * @date 创建时间：2017年7月21日 下午5:35:06 
 * 参数特殊字符过滤
 */
public class XSSHttpServletRequest extends HttpServletRequestWrapper {
	public XSSHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        // 返回值之前 先进行过滤
        return XSSShieldUtil.stripXss(super.getParameter(XSSShieldUtil.stripXss(name)));
    }

    @Override
    public String[] getParameterValues(String name) {
        // 返回值之前 先进行过滤
        String[] values = super.getParameterValues(XSSShieldUtil.stripXss(name));
        if(values != null){
            for (int i = 0; i < values.length; i++) {
                values[i] = XSSShieldUtil.stripXss(values[i]);
            }
        }
        return values;
    }

}