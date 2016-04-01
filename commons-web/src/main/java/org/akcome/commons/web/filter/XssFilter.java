package org.akcome.commons.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 编写定制的XssFilter，将request请求代理，
 * 覆盖getParameter和getHeader方法将参数名和参数值里的指定半角字符，
 * 强制替换成全角字符。使得在业务层的处理时不用担心会有异常输入内容
 * 
 * 使用方式 ：web.xml 加入
 *  <filter>  
		<filter-name>xssFilter</filter-name>  
		<filter-class>filter.XssFilter</filter-class>  
	</filter>  
	<filter-mapping>  
		<filter-name>xssFilter</filter-name>  
		<url-pattern>/*</url-pattern>  
	</filter-mapping>  
 * @author peng_wang
 * @date 2015年11月26日 
 *
 */
public class XssFilter implements Filter {
	public void init(FilterConfig config) throws ServletException {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
				(HttpServletRequest) request);
		chain.doFilter(xssRequest, response);
	}

	public void destroy() {
	}
}
