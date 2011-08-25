package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommerce.model.ShoppingCart;
import com.supinfo.supcommerce.security.LoginModule;

@WebServlet(urlPatterns="/updateCart")
@ServletSecurity(@HttpConstraint(rolesAllowed = { LoginModule.CUSTOMER_ROLE }))
public class UpdateCartServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Map<String, String[]> parameters = req.getParameterMap();
		
		ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
		
		for (Entry<String, String[]> param : parameters.entrySet()) {
			if(param.getKey().startsWith("quantity")) {
				String cartItemIdParam = param.getKey().substring(param.getKey().lastIndexOf('.') + 1);
				Integer cartItemId = Integer.valueOf(cartItemIdParam);
				
				Integer quantity = Integer.valueOf(param.getValue()[0]);
				cart.setCartItemQuantity(cartItemId, quantity);
			}
		}
		
		resp.sendRedirect("/cart");
	}
	
}
