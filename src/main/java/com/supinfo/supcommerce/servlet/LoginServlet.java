package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns= { "/login", "/ajax/login" })
public class LoginServlet extends HttpServlet {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
	}
	
	/**
	 * @see http://stackoverflow.com/questions/199099/how-to-manage-a-redirect-request-after-a-jquery-ajax-call
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			req.login(username, encryptPassword(password));
			String redirectUrl = req.getContextPath();
			if(isAjaxCall(req)) {
				resp.addHeader("Location", redirectUrl);
			} else {
				resp.sendRedirect(redirectUrl);
			}
		} catch (ServletException e) {
			List<String> errors = new ArrayList<String>();
			errors.add("Wrong username and/or password.");
			
			req.setAttribute("errors", errors);
			
			doGet(req, resp);
		}
	}
	
	private boolean isAjaxCall(HttpServletRequest request) {
		return request.getRequestURI().startsWith("/ajax");
	}
	
	private String encryptPassword(String password) {
		String result = null;
		try {
			result = new String(MessageDigest.getInstance("SHA-1").digest(password.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}
		
}
