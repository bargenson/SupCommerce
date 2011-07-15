package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.model.Category;

@WebServlet(urlPatterns="/categories")
public class ListCategoryServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CategoryDao categoryService = DaoFactory.getDaoFactory().getCategoryDao();
		
		List<Category> categories = categoryService.getAllCategories();
		
		req.setAttribute("categories", categories);
		
		req.getRequestDispatcher("/jsp/listCategory.jsp").forward(req, resp);
	}

}
