package com.strabl.service.product.bdd;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Display {

	public String displayitem(Itemlist item) {
		if(item.getFullProductDetails().contains(item.getCategory())) {
			return item.getCategory();
		}
		return null;
	}
	
	public String getproduct(Itemlist item)
	{
		if(item.addProductToCart().contains(item.getAddProduct()))
		{
			return item.getAddProduct();
		}
		return null;
	}
	
	public String getbrowse(Itemlist item)
	{
		if(item.browseWithoutSignUp().contains(item.getBrowse()))
		{
			return item.getBrowse();
		}
		return null;
	}

	}

