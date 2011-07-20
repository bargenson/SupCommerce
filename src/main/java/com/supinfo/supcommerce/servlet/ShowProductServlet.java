package com.supinfo.supcommerce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.model.Product;

@WebServlet(urlPatterns="product")
public class ShowProductServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String productIdParam = req.getParameter("id");
		Long productId = Long.valueOf(productIdParam);
		
		Product product = DaoFactory.getDaoFactory().getProductDao().findProductById(productId);
		
		req.setAttribute("product", product);
		req.getRequestDispatcher("/jsp/showProduct.jsp").forward(req, resp);
	}

}
