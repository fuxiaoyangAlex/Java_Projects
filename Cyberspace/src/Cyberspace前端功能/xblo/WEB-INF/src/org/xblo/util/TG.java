package org.xblo.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class TG {
	private static HttpServletRequest request = null;

	public void setRequest(HttpServletRequest request) {
		TG.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public static TNS GetFromXML() {
		TNS tns = null;
		try {
			tns = new TNS();
			SAXBuilder sb = new SAXBuilder();
			Document doc;
			doc = sb.build(XT.TNSFILE);
			Element root = doc.getRootElement();
			String driver = root.getChildText("driver");
			tns.setDriver(driver);
			String url = root.getChildText("url");
			tns.setUrl(url);
			String username = root.getChildText("username");
			tns.setUsername(username);
			String password = root.getChildText("password");
			tns.setPassword(password);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tns;
	}

	public static void main(String[] args) {
		TG.GetFromXML();
	}
}
