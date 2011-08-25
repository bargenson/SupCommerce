package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommerce.model.ShoppingCart;
import com.supinfo.supcommerce.security.LoginModule;

@WebServlet(urlPatterns="/cart")
@ServletSecurity(@HttpConstraint(rolesAllowed = { LoginModule.CUSTOMER_ROLE }))
public class CartServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
		if(cart != null) {
			req.setAttribute("cartItems", cart.getItems());
		} else {
			req.setAttribute("cartItems", Collections.EMPTY_LIST);
		}
		req.getRequestDispatcher("/jsp/cart.jsp").forward(req, resp);
	}

}
