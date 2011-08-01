package com.supinfo.supcommerce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= { "/login", "/ajax/login" })
//@ServletSecurity(@HttpConstraint(transportGuarantee=TransportGuarantee.CONFIDENTIAL))
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
	}
	
//	/**
//	 * @see http://stackoverflow.com/questions/199099/how-to-manage-a-redirect-request-after-a-jquery-ajax-call
//	 */
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		
//		String username = req.getParameter("j_username");
//		String password = req.getParameter("j_password");
//		
//		try {
//			req.login(username, password);
//			String redirectUrl = req.getContextPath();
//			if(isAjaxCall(req)) {
//				resp.addHeader("Location", redirectUrl);
//			} else {
//				resp.sendRedirect(redirectUrl);
//			}
//		} catch (ServletException e) {
//			List<String> errors = new ArrayList<String>();
//			errors.add("Wrong username and/or password.");
//			
//			req.setAttribute("errors", errors);
//			
//			doGet(req, resp);
//		}
//	}
//	
//	private boolean isAjaxCall(HttpServletRequest request) {
//		return request.getRequestURI().startsWith("/ajax");
//	}
		
}
