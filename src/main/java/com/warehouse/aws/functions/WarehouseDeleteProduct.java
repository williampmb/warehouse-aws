package com.warehouse.aws.functions;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.warehouse.aws.models.HttpRequestWarehouse;
import com.warehouse.aws.models.HttpResponseWarehouse;
import com.warehouse.aws.services.ProductService;

public class WarehouseDeleteProduct implements RequestHandler<HttpRequestWarehouse, HttpResponseWarehouse> {

	@Override
	public HttpResponseWarehouse handleRequest(HttpRequestWarehouse request, Context context) {
		HttpResponseWarehouse response = new HttpResponseWarehouse();
		Map<String, String> pathParameters = request.getPathParameters();

		if (pathParameters == null) {
			response.setStatusCode("500");
		} else {
			String id = pathParameters.get("id");

			boolean deleted = ProductService.deteleProduct(id);

			if (!deleted) {
				response.setStatusCode("500");
			}
		}

		return response;
	}

}
