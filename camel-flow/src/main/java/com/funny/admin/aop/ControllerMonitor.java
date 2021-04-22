package com.funny.admin.aop;

import com.alibaba.fastjson.JSON;
import com.funny.admin.common.exception.BusinessException;
import com.funny.admin.model.entity.AdminUserEntity;
import com.funny.admin.model.entity.Log;
import com.funny.admin.model.enums.LogInfo;
import com.funny.admin.service.LogService;
import com.funny.combo.core.result.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/** 
 * @author  fangli
 * @date 创建时间：2017年7月9日 下午1:01:47 
 */
@Component
@Aspect
public class ControllerMonitor {
	
	private static Logger logger = LoggerFactory.getLogger(ControllerMonitor.class);
	
	@Autowired
	private LogService logService;

	@Pointcut("execution(* com.funny.admin.controller..*.*(..))")
	public void init() {

	}
	
	@Before(value = "init()")
	public void before(JoinPoint jp) {
		//Object[] args = jp.getArgs();
		//logger.info("---------方法执行前执行.....");
	}

	@AfterReturning(value = "init()")
	public void afterReturning() {
		//logger.info("---------方法执行完执行.....");
	}

	@AfterThrowing(value = "init()")
	public void throwss() {
		//logger.info("--------方法异常时执行.....");
	}

	@After(value = "init()")
	public void after() {
		//logger.info("-------方法最后执行.....");
	}

	/**
	 * 记录日志,全局异常处理
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	@Around(value = "init()")
	public Object around(ProceedingJoinPoint joinPoint) throws Exception {
		
		boolean isLog = false;//是否记录日志
		
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] params = joinPoint.getArgs();
		if(params.length > 0){
			
			for (int i = 0; i < params.length; i++) {
				
				map.put("params"+i, params[i]);
			}
		}
		
		String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        
        logger.info("-------【方法 " + targetName + "." + methodName + "() 执行开始】.....");

		AdminUserEntity account = null;
		Log log = new Log();
		LogInfo logInfo = null;
		
        Class<?> targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods(); 
        
        for (int i = 0; i < methods.length; i++) {
        	
			if(methodName.equals(methods[i].getName())){
				
				logInfo = methods[i].getAnnotation(LogInfo.class);
				
				if(null != logInfo){
					
					logger.info("记录日志:" + logInfo.operationContent());
					
					isLog = true;
					account = (AdminUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					
					logger.info("记录日志:" + logInfo.operationContent());
					log.setContent(logInfo.operationContent());
					log.setLog(logInfo.logType());
//					log.setIp(IPUtil.getIpAddress(request));
					log.setParams(JSON.toJSONString(map));
					log.setAccount(account.getUsername());
					log.setIsDelete(false);
				}
			}
		}
        
		Object process = null;
		try {
			
			process = joinPoint.proceed();
		} catch (Throwable e) {
			
			if(isLog){
				
				logger.info("记录异常日志:" + e.getMessage());
				
				if (e instanceof BusinessException) {
					
					log.setContent(e.getMessage());
				}else if(e instanceof IllegalArgumentException){
					
					log.setContent(e.getMessage());
				}else if(e instanceof UsernameNotFoundException){
					
					log.setContent(e.getMessage());
				}else{
					
					log.setContent("服务器内部错误!");
				}
				
			}
			
			exceptionHandler(response,e);
		}
		
		if(isLog){
			
			logger.info("记录成功日志:" + logInfo.operationContent() + "-----成功！");
			
			logService.addLog(log);
		}
		
		logger.info("-------【方法 " + targetName + "." + methodName + "() 执行结束】.....");
		
		return process;
	}
	
	/**
	 * 全局异常处理
	 * @param response
	 * @param e
	 * @throws Exception
	 */
	public void exceptionHandler(HttpServletResponse response, Throwable e) throws Exception {
		CommonResult result = CommonResult.fail("");
		
		if (e instanceof IllegalArgumentException) {
			
			if (StringUtils.isNotBlank(e.getMessage())) {
				
				result.setMessage(e.getMessage());
			} else {
				result.setMessage(e.getMessage());
			}
		}
		result.setMessage(e.getMessage());
		
		byte[] bytes = JSON.toJSONString(result).getBytes();
		response.setContentType("application/json;charset=UTF-8");
		response.getOutputStream().write(bytes);
	}
}