package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.model.Customer;

@WebServlet(urlPatterns= { "/register", "/ajax/register" })
public class RegisterServlet extends HttpServlet {
	
	private final static DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	private final static Logger LOGGER = LoggerFactory.getLogger(RegisterServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String usernameParam = req.getParameter("username");
		String passwordParam = req.getParameter("password");
		String confirmationParam = req.getParameter("confirmation");
		String emailParam = req.getParameter("email");
		String firstNameParam = req.getParameter("firstName");
		String lastNameParam = req.getParameter("lastName");
		String dateOfBirthParam = req.getParameter("dateOfBirth");
		
		List<String> errors = new ArrayList<String>();
		
		try {
			Date dateOfBirth = DATE_FORMAT.parse(dateOfBirthParam);
			
			Customer customer = new Customer(usernameParam, passwordParam, confirmationParam, 
												firstNameParam, lastNameParam, dateOfBirth, emailParam);
			
			Set<ConstraintViolation<Customer>> violations = validateModel(customer);
			
			if(violations.isEmpty()) {
				customer = (Customer) DaoFactory.getDaoFactory().getUserDao().register(customer);
				if(customer != null) {
					String redirectUrl = "/login";
					if(isAjaxCall(req)) {
						resp.addHeader("Location", redirectUrl);
					} else {
						resp.sendRedirect(redirectUrl);
						return;
					}
				} else {
					errors.add("Sorry, but it is impossible to register you. Please retry later.");
					LOGGER.error("Customer not persisted !");
				}
			} else {
				for (ConstraintViolation<Customer> constraintViolation : violations) {
					errors.add(constraintViolation.getPropertyPath().toString() + constraintViolation.getMessage());
				}
			}
		} catch (ParseException e) {
			errors.add("Bad date of birth format.");
			LOGGER.debug("Bad date format: " + dateOfBirthParam, e);
		}
		
		req.setAttribute("errors", errors);
		
		doGet(req, resp);
	}
	
	private <T> Set<ConstraintViolation<T>> validateModel(T model) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		return validator.validate(model);
	}
	
	private boolean isAjaxCall(HttpServletRequest request) {
		return request.getRequestURI().startsWith("/ajax");
	}
	
}
