package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommerce.model.Category;
import com.supinfo.supcommerce.model.Product;
import com.supinfo.supcommerce.service.CategoryService;
import com.supinfo.supcommerce.service.ProductService;
import com.supinfo.supcommerce.service.ServiceFactory;

@WebServlet(urlPatterns= { "/", "/products" })
public class ListProductServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ProductService productService = ServiceFactory.getInstance().getProductService();
		CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
		
		List<Product> products = productService.getAllProducts();
		List<Category> categories = categoryService.getAllCategories();
		
		req.setAttribute("categories", categories);
		req.setAttribute("products", products);
		
		req.getRequestDispatcher("/jsp/listProduct.jsp").forward(req, resp);
	}

}
