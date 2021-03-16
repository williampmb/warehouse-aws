package com.warehouse.aws.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.warehouse.aws.models.Product;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

public class ProductService {
	private static final String WAREHOUSE_BUCKECT = "my-warehouse-9821"; 
	private static final String WAREHOUSE_FILE    = "storage.json"; 
	
	public static BufferedReader getStorageFile() {
		S3Client s3Cli = S3Client.builder().region(Region.US_EAST_1).build();
		ResponseInputStream<?> objectData = s3Cli.getObject(GetObjectRequest.builder()
				.bucket(WAREHOUSE_BUCKECT)
				.key(WAREHOUSE_FILE)
				.build());
		InputStreamReader isr = new InputStreamReader(objectData);
		BufferedReader br = new BufferedReader(isr);
		return br;
	}

	public static Product getProduct(String id ) {
		BufferedReader br = getStorageFile();
		
		Gson gson = new Gson();
		
		List<Product> products = arrayToList(gson.fromJson(br, Product[].class));
		
		Long findId = Long.parseLong(id);
		
		Product Product = products.stream().filter(prod -> prod.getId() == findId).findAny().orElse(null);
		System.out.println("******************************");
		System.out.println("Product : " + Product.toString());
		System.out.println("******************************");
		return Product;
	}
	
	public static Product[] getAllProducts() {
		BufferedReader br = getStorageFile();
		
		Product[] products = null;
		Gson gson = new Gson();
		
		products = gson.fromJson(br, Product[].class);
		
		return products;
	}
	
	protected static List<Product> arrayToList(Product[] prods){
		return Arrays.asList(prods);
	}
}
