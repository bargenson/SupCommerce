package com.supinfo.supcommerce.servlet;

import java.io.IOException;

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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User user = DaoFactory.getDaoFactory().getUserDao().authenticate(username, password);
		
		if(username.equals("plop")) { // TODO Just for tests
			req.getSession().setAttribute("current_user", user);
			req.getRequestDispatcher("/jsp/logged.jsp").forward(req, resp);
		} else {
			//resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
		}
	}
	
}
