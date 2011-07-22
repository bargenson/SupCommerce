package com.supinfo.supcommerce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.model.Category;

@WebServlet(urlPatterns="/category")
public class ShowCategoryServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String categoryIdParam = req.getParameter("id");
		
		if(categoryIdParam != null) {
			Long categoryId = Long.valueOf(categoryIdParam);
			
			Category category = DaoFactory.getDaoFactory().getCategoryDao().getCategoryByIdWithProducts(categoryId);
			
			if(category != null) {
				req.setAttribute("category", category);
				req.getRequestDispatcher("/jsp/showCategory.jsp").forward(req, resp);
				return;
			}
		}
		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}

}
