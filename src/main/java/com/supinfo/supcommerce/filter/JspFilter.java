package com.supinfo.supcommerce.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns="*.jsp")
public class JspFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
		// Do nothing
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse httpResponse = (HttpServletResponse) resp;
		httpResponse.sendRedirect("/");
	}

	@Override
	public void destroy() {
		// Do nothing
	}
	
}
