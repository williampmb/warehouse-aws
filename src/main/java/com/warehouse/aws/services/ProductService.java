package com.warehouse.aws.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.warehouse.aws.models.Product;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public class ProductService {
	private static final String WAREHOUSE_BUCKECT = "my-warehouse-9821";
	private static final String WAREHOUSE_FILE = "storage.json";

	public static List<Product> loadProducts() {
		S3Client s3Cli = S3Client.builder().region(Region.US_EAST_1).build();
		ResponseInputStream<?> objectData = s3Cli
				.getObject(GetObjectRequest.builder().bucket(WAREHOUSE_BUCKECT).key(WAREHOUSE_FILE).build());
		InputStreamReader isr = new InputStreamReader(objectData);
		BufferedReader br = new BufferedReader(isr);

		Gson gson = new Gson();
		List<Product> products = arrayToList(gson.fromJson(br, Product[].class));

		return products;
	}
	
	private static boolean saveProducts(String jsonString) {
		S3Client s3Cli = S3Client.builder().region(Region.US_EAST_1).build();
		
		PutObjectResponse s3Response = s3Cli.putObject(PutObjectRequest.builder()
				.bucket(WAREHOUSE_BUCKECT)
				.key(WAREHOUSE_FILE)
				.build(),
				RequestBody.fromString(jsonString));
		
		return s3Response.sdkHttpResponse().isSuccessful();
	}

	public static Product getProduct(String id) {
		Long findId = Long.parseLong(id);

		List<Product> products = loadProducts();
		
		Product Product = products.stream().filter(prod -> prod.getId() == findId).findAny().orElse(null);
		return Product;
	}

	public static List<Product> getAllProducts() {
		List<Product> products = loadProducts();

		return products;
	}

	protected static List<Product> arrayToList(Product[] prods) {
		List<Product> ans = new ArrayList<Product>(Arrays.asList(prods));
		return ans;
	}

	public static boolean addProduct(String body) {

		Product newProduct = jsonToProduct(body);

		List<Product> products = getAllProducts();

		Product existsProdWithId = products.stream().filter(prod -> prod.getId() == newProduct.getId()).findAny()
				.orElse(null);

		if (existsProdWithId != null)
			return false;

		products.add(newProduct);
		
		String productsToJson = productsToJson(products);
		boolean saveProducts = saveProducts(productsToJson);

		return saveProducts;
	}

	private static Product jsonToProduct(String body) {
		Gson gson = new Gson();
		Product product = gson.fromJson(body, Product.class);
		
		return product;
	}
	
	private static String productsToJson(List<Product> products) {
		Gson gson = new Gson();

		String json = gson.toJson(products);
		return json;
	}

	public static boolean deteleProduct(String idStr) {
		List<Product> products = loadProducts();
		
		Long id = Long.parseLong(idStr);
		
		products.removeIf(prod -> prod.getId() == id);
		
		String productsToJson = productsToJson(products);
		boolean saveProducts = saveProducts(productsToJson);
		
		return saveProducts;
	}
	

}
