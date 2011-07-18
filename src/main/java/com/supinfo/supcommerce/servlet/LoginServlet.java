package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.model.User;

@WebServlet(urlPatterns= { "/login", "/ajax/login" })
public class LoginServlet extends HttpServlet {

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
		
		User user = DaoFactory.getDaoFactory().getUserDao().authenticate(username, password);
		
		if("plop".equals(username)) { // TODO Just for tests
			req.getSession().setAttribute("current_user", user);			
			
			String redirectUrl = "/";
			if(isAjaxCall(req)) {
				resp.addHeader("Location", redirectUrl);
			} else {
				resp.sendRedirect(redirectUrl);
			}
		} else {
			List<String> errors = new ArrayList<String>();
			errors.add("Wrong username and/or password.");
			
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
		}
	}
	
	private boolean isAjaxCall(HttpServletRequest request) {
		return request.getRequestURI().startsWith("/ajax");
	}
	
}
