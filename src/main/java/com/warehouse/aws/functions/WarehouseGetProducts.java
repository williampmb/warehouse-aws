package com.warehouse.aws.functions;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.warehouse.aws.models.HttpRequestWarehouse;
import com.warehouse.aws.models.HttpResponseWarehouse;
import com.warehouse.aws.models.Product;
import com.warehouse.aws.services.ProductService;

public class WarehouseGetProducts implements RequestHandler<HttpRequestWarehouse, HttpResponseWarehouse> {

	@Override
	public HttpResponseWarehouse handleRequest(HttpRequestWarehouse request, Context context) {
		Map<String, String> pathParameters = request.getPathParameters();
		
		HttpResponseWarehouse response;
		if (pathParameters == null) {
			List<Product> products = ProductService.getAllProducts();

			response = new HttpResponseWarehouse(products);
		}else {
			String prodId = pathParameters.get("id");
			
			Product product = ProductService.getProduct(prodId);
			
			response = new HttpResponseWarehouse(product);
		}
		
		return response;
	}

}
