package com.warehouse.aws.models;

import java.util.Map;

public class HttpRequestWarehouse {

	private Map<String,String> queryStringParameters;
	private Map<String,String> pathParameters;
	private String body;

	public Map<String, String> getQueryStringParameters() {
		return queryStringParameters;
	}
	public void setQueryStringParameters(Map<String, String> queryStringParameters) {
		this.queryStringParameters = queryStringParameters;
	}
	public Map<String, String> getPathParameters() {
		return pathParameters;
	}
	public void setPathParameters(Map<String, String> pathParameters) {
		this.pathParameters = pathParameters;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
