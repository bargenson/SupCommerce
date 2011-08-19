package com.supinfo.supcommerce.mail;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public abstract class MailInfoWithFile implements MailInfo {

	@Override
	public String getContent() {
		StringBuilder result = new StringBuilder();
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(getHtmlFile());
			while (scanner.hasNextLine()) {
				result.append(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(scanner != null) scanner.close();
		}
		
		return result.toString();
	}

	protected abstract InputStream getHtmlFile() throws FileNotFoundException;

}
