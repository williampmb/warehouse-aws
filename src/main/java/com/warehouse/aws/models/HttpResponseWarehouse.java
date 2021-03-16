package com.warehouse.aws.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class HttpResponseWarehouse {

	private String body;
	private String statusCode = "200";
	private Map<String, String> headers = new HashMap<>();

	public HttpResponseWarehouse() {
		this.headers.put("Content-Type", "application/json");
	}

	public HttpResponseWarehouse(Product product) {
		this();
		Gson gson = new Gson();
		String json = gson.toJson(product);
		this.body = json;
	}

	public HttpResponseWarehouse(List<Product> products) {
		this();
		Gson gson = new Gson();
		String json = gson.toJson(products);
		this.body = json;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	
}
