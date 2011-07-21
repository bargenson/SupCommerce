package com.supinfo.supcommerce.servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommerce.dao.DaoFactory;

@WebServlet(urlPatterns= { "/picture/display" })
public class DisplayPictureServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String productIdParam = req.getParameter("productId");
		Long productId = Long.valueOf(productIdParam);
		
		byte[] picture = DaoFactory.getDaoFactory().getPictureDao().findPictureByProductId(productId);
		
		if(picture != null) {
			resp.setContentType("images/*");
			
			ServletOutputStream outputStream = null;
			BufferedOutputStream bos = null;
			try {
				outputStream = resp.getOutputStream();
				bos = new BufferedOutputStream(outputStream);
				bos.write(picture);
			} finally {
				if(outputStream != null) {
					if(bos != null) {
						bos.flush();
						bos.close();
					} else {
						outputStream.close();
					}
				}
			}
		} else {
			req.getRequestDispatcher("/images/noImageAvailable.jpg").forward(req, resp);
		}
	}

}
