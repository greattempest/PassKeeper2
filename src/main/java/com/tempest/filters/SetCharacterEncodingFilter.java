package com.tempest.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@WebFilter(value="SetCharacterEncodingFilter",urlPatterns = {"/filter"})
public class SetCharacterEncodingFilter implements Filter {
	protected String encoding = null;

	protected FilterConfig filterConfig = null;

	protected boolean ignore = true;
	
	@Override
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if ((this.ignore) || (request.getCharacterEncoding() == null)) {
			String encoding = selectEncoding(request);
			if (encoding != null) {
				request.setCharacterEncoding(encoding);
			}
		}

		String path = ((HttpServletRequest) request).getServletPath();
		if (((HttpServletRequest) request).getSession().getAttribute("userinfo") == null) {// session不存在需要拦截
			String contextPath = ((HttpServletRequest) request).getContextPath();
//      System.out.println("ContextPath="+contextPath);  
			((HttpServletResponse) response).sendRedirect(contextPath + "/login.jsp?toPath=" + path);// 记录下登录后需要跳转到的页面，比如：/logined/account.jsp
		} else {
			chain.doFilter(request, response);
		}

		chain.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null)
			this.ignore = true;
		else if (value.equalsIgnoreCase("true"))
			this.ignore = true;
		else if (value.equalsIgnoreCase("yes"))
			this.ignore = true;
		else
			this.ignore = false;
	}

	protected String selectEncoding(ServletRequest request) {
		return this.encoding;
	}
}