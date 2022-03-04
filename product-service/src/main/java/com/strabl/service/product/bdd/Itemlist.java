package com.strabl.service.product.bdd;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class Itemlist {

	private Integer price;
	private String category;
	private String subcategory;

	public Itemlist(int price, String category, String subcategory) {
		super();
		this.price = price;
		this.category = category;
		this.subcategory = subcategory;
	
	}
	public int getPrice() 
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	public String getCategory() 
	{
		return category;
	}
	public void setCategory(String category) 
	{
		this.category = category;
	}
	public String getSubcategory() 
	{
		return subcategory;
	}
	public void setSubcategory(String subcategory)
	{
		this.subcategory = subcategory;
	}
	
public List<String>getFullProductDetails()
{
	List<String> detailList = new ArrayList<>();
	detailList.add(category);
	detailList.add(subcategory);
	return null;
	
}


private String addProduct;

	public Itemlist(String addProduct)
	{
		super();
		
		this.addProduct = addProduct;
	}

	public String getAddProduct() 
	{
		return addProduct;
	}

	public void setAddProduct(String addProduct)
	{
		this.addProduct = addProduct;
	}
	 
	public List<String>addProductToCart()
	{
		List<String> productList = new ArrayList<>();
		productList.add(addProduct);
		return null;

   }
	
	
	private String browse;

	public String getBrowse() {
		return browse;
	}
	public void setBrowse(String browse) {
		this.browse = browse;
	}
	public List<String>browseWithoutSignUp()
	{
		List<String> productList = new ArrayList<>();
		productList.add(browse);
		return null;

   }
	
}
