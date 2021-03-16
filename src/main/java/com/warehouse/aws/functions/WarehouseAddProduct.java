package com.warehouse.aws.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.warehouse.aws.models.HttpRequestWarehouse;
import com.warehouse.aws.models.HttpResponseWarehouse;
import com.warehouse.aws.services.ProductService;

public class WarehouseAddProduct implements RequestHandler<HttpRequestWarehouse, HttpResponseWarehouse> {

	@Override
	public HttpResponseWarehouse handleRequest(HttpRequestWarehouse request, Context context) {
	
		String body = request.getBody();
		boolean success = ProductService.addProduct(body);
		HttpResponseWarehouse response = new HttpResponseWarehouse();

		if (!success) {
			response.setStatusCode("500");
		}

		return response;
	}
}
