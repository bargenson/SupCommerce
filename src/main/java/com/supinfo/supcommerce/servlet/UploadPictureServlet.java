package com.supinfo.supcommerce.servlet;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.supinfo.supcommerce.security.LoginModule;

@MultipartConfig
@WebServlet(urlPatterns="/picture/upload")
@ServletSecurity(@HttpConstraint(rolesAllowed= { LoginModule.ADMIN_ROLE }))
public class UploadPictureServlet extends HttpServlet {	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Part part = req.getPart("data");
		byte[] data = resizePicture(getByteArray(part), 300);
		req.getSession().setAttribute("com.supinfo.supcommerce.upload", data);
		resp.getWriter().print(req.getSession().getId());
	}

	private byte[] getByteArray(Part part) throws IOException {
		byte[] result = new byte[(int) part.getSize()];
		BufferedInputStream bufferedInputStream = new BufferedInputStream(part.getInputStream());
		bufferedInputStream.read(result);
		return result;
	}
	
	public byte[] resizePicture(byte[] data, int width) throws IOException {
		return resizePicture(data, width, 0);
	}

	public byte[] resizePicture(byte[] data, int width, int height) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(data));

		int calcHeight = height > 0 ? height : (width * bufferedImage.getHeight() / bufferedImage.getWidth());

        BufferedImage scaledBI = new BufferedImage(width, calcHeight, BufferedImage.TYPE_INT_RGB);
        
		Graphics2D g = scaledBI.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(bufferedImage, 0, 0, width, calcHeight, null);
        g.dispose();
        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(scaledBI, "jpg", byteArrayOutputStream);
        
        return byteArrayOutputStream.toByteArray();
	}

}
