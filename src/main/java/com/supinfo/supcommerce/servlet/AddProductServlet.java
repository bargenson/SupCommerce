package com.supinfo.supcommerce.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.model.Category;
import com.supinfo.supcommerce.model.Product;
import com.supinfo.supcommerce.security.LoginModule;

@MultipartConfig
@WebServlet(urlPatterns={ "/addProduct", "/ajax/addProduct" })
@ServletSecurity(@HttpConstraint(rolesAllowed = { LoginModule.ADMIN_ROLE }))
public class AddProductServlet extends HttpServlet {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(RegisterServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		List<Category> categories = DaoFactory.getDaoFactory().getCategoryDao().getAllCategories();
				
		req.setAttribute("categories", categories);
		req.getRequestDispatcher("/jsp/addProduct.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String nameParam = req.getParameter("name");
		String descriptionParam = req.getParameter("description");
		String priceParam = req.getParameter("price");
		BigDecimal price = new BigDecimal(priceParam);
		String categoryIdParam = req.getParameter("category");
		Long categoryId = Long.valueOf(categoryIdParam);
		
		byte[] picture;
		if(isAjaxCall(req)) {
			String uploadPictureAttribute = "com.supinfo.supcommerce.upload";
			picture = (byte[]) req.getSession().getAttribute(uploadPictureAttribute);
			req.getSession().removeAttribute(uploadPictureAttribute);
		} else {
			Part picturePart = req.getPart("picture");
			picture = getByteArray(picturePart);
		}
		
		List<String> errors = new ArrayList<String>();
		
		Category category = DaoFactory.getDaoFactory().getCategoryDao().findCategoryById(categoryId);
		
		Product product = new Product(nameParam, descriptionParam, price, category, picture);
		
		Set<ConstraintViolation<Product>> violations = validateModel(product);
		
		if(violations.isEmpty()) {
			product = DaoFactory.getDaoFactory().getProductDao().addProduct(product);
			if(product != null) {
				String redirectUrl = "/category?id=" + categoryId;
				if(isAjaxCall(req)) {
					resp.addHeader("Location", redirectUrl);
				} else {
					resp.sendRedirect(redirectUrl);
					return;
				}
			} else {
				errors.add("Sorry, but it is impossible to add a new product. Please retry later.");
				LOGGER.error("Product not persisted !");
			}
		} else {
			for (ConstraintViolation<Product> constraintViolation : violations) {
				errors.add(constraintViolation.getMessage());
			}
		}
		
		req.setAttribute("errors", errors);
		
		doGet(req, resp);
	}

	private byte[] getByteArray(Part part) throws IOException {
		byte[] result = null;
		if(part != null) {
			result = new byte[(int) part.getSize()];
			BufferedInputStream bufferedInputStream = new BufferedInputStream(part.getInputStream());
			bufferedInputStream.read(result);
		}
		return result;
	}
	
	private <T> Set<ConstraintViolation<T>> validateModel(T model) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		return validator.validate(model);
	}
	
	private boolean isAjaxCall(HttpServletRequest request) {
		return request.getRequestURI().startsWith("/ajax");
	}
	
}
