package com.supinfo.supcommerce.mail;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterConfirmationMailInfo extends MailInfoWithFile {

	@Override
	public String getSubject() {
		return "Register Confirmation";
	}

	@Override
	public InputStream getHtmlFile() throws FileNotFoundException {
		return this.getClass().getClassLoader()
				.getResourceAsStream("mails/RegisterConfirmation.html");
	}

}
