package edu.escuelaing.arep.httpServer;

import java.net.*;
import java.util.*;
import java.util.logging.*;

/**
 * Request
 * 
 */
public class Request {

	private String method;
	private String reqURI;
	private String HTTPVersion;
	private URI uri;
	private Map<String, String> query;

	/**
	 * Method constructor
	 * 
	 * @param requestLine
	 */
	public Request(String req) {
		try {
			String[] components = req.split(" ");
			method = components[0];
			this.reqURI = components[1];
			HTTPVersion = components[2];
			setUri(new URI(reqURI));
			query = parseQuery(uri.getQuery());
		} catch (URISyntaxException ex) {
			Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private Map<String, String> parseQuery(String query) {
		if (query == null) {
			return null;
		}
		Map<String, String> res = new HashMap<String, String>();
		String[] nameValuePairs = query.split("&");
		for (String name : nameValuePairs) {
			int index = name.indexOf("=");
			if (index != -1) {
				res.put(
						name.substring(0, index),
						name.substring(index + 1));
			}
		}
		return res;
	}
	
	/**
	 * Method to obtains the uri request
	 * 
	 * @return
	 */
	public String getRequestURI() {
		return this.reqURI;
	}

	/**
	 * Method to convert the uri to string
	 * 
	 * @return
	 */
	public String toString() {
		return method + " " + reqURI + " " + HTTPVersion + "\n\r" + getUri() + "\n\r" + "Query: " + query;
	}

	/**
	 * Method to obtains the uri
	 * 
	 * @return
	 */
	public URI getUri() {
		return uri;
	}

	/**
	 * Method that saves the uri
	 * 
	 * @param theuri
	 */
	public void setUri(URI theuri) {
		this.uri = theuri;
	}

	
	
}