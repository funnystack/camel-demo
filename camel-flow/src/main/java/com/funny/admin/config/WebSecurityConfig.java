package com.funny.admin.config;

import com.funny.admin.security.*;
import com.funny.admin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/** 
 * @author fangli
 * @date 创建时间：2017年7月13日 下午12:18:05 
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AccountService accountService;
	
	@Bean
	public LoginFailureHandler getLoginFailureHandler() {  
		LoginFailureHandler loginFailureHandler = new LoginFailureHandler();
        return loginFailureHandler;  
    }
	
	@Bean
	public LoginSuccessHandler getLoginSuccessHandler() {  
		LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
        return loginSuccessHandler;  
    }
	
	@Bean
	public LogOutHandler getLogOutHandler() {  
		LogOutHandler logOutHandler = new LogOutHandler();
        return logOutHandler;  
    }
	
	@Bean
	public SessionAuthStrategy getSysSessionAuthenticationStrategy() {
		SessionAuthStrategy sysSessionAuthenticationStrategy = new SessionAuthStrategy();
		return sysSessionAuthenticationStrategy;  
	}

	@Override  
    public void configure(WebSecurity web) throws Exception {  
        // 设置不拦截规则  
        web.ignoring().antMatchers(
        		"/css/**",
        		"/js/**",
        		"/images/**",
        		"/fonts/**",
        		"/plugins/**",
        		"/register/**",
				"/monitor/**",
				"/actuator/**",
        		"/error/**"
        		);
    } 
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		 http.
		 exceptionHandling()
		 	.accessDeniedPage("/403")
		 	.and()
         .authorizeRequests()
             .antMatchers("/login", "/getSystemTime").permitAll()
             .anyRequest().authenticated();
         http.csrf().disable().formLogin()
             .loginPage("/login")
             .usernameParameter("username")
             .passwordParameter("password")
             .loginProcessingUrl("/login/do")
             .failureHandler(getLoginFailureHandler())
             .successHandler(getLoginSuccessHandler());
         http.rememberMe().key("webmvc#FD637E6D9C0F1A5A67082AF56CE32485");
         http.logout().addLogoutHandler(getLogOutHandler())
	         .invalidateHttpSession(true)
	         .deleteCookies("remove")
	         .logoutUrl("/logout/do").logoutSuccessUrl("/login?login_out=1");
         http.sessionManagement()
         	 .maximumSessions(1)
         	 .maxSessionsPreventsLogin(true)
         	 .expiredUrl("/login?overdue=1");
         // iframe 嵌套
         http.headers().frameOptions().disable();
    }
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// 自定义UserDetailsService 
        //auth.userDetailsService(employeeService).passwordEncoder(new Md5PasswordEncoder());  
		auth.userDetailsService(accountService).passwordEncoder(new CHPasswordEncoder());;
    }
}
