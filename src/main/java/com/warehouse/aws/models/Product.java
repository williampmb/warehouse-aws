package com.warehouse.aws.models;

public class Product {

	private Long id;
	private String branch;
	private String name;
	private int quantity;

	public Product() {
	}

	public Product(Long id, String branch, String name, int quantity) {
		this.id = id;
		this.branch = branch;
		this.name = name;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
