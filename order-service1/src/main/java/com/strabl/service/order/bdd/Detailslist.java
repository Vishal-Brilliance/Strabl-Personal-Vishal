package com.strabl.service.order.bdd;



public class Detailslist {

	public String orderdetails(Orderlist order) {
		if(order.viewOrderDetails().contains(order.getOrderdetails()))
		{
			return order.getOrderdetails();
		}
		return null;
	}
}
