package com.strabl.service.order.bdd;

import java.util.ArrayList;
import java.util.List;

public class Orderlist {
	
	private String orderdetails;
	private String orderid;
	private String orderimage;
	private String date;
	private String type;
	private Integer amount;
	
	public Orderlist(String orderdetails, String orderid, String orderimage, String date, String type, Integer amount) {
		super();
		this.orderdetails = orderdetails;
		this.orderid = orderid;
		this.orderimage = orderimage;
		this.date = date;
		this.type = type;
		this.amount = amount;
	}
	
	public Orderlist(String orderdetail) {
		// TODO Auto-generated constructor stub
	}

	public String getOrderdetails() {
		return orderdetails;
	}
	public void setOrderdetails(String orderdetails) {
		this.orderdetails = orderdetails;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrderimage() {
		return orderimage;
	}
	public void setOrderimage(String orderimage) {
		this.orderimage = orderimage;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public List<String>viewOrderDetails()
	{
		List<String> viewList = new ArrayList<>();
		viewList.add(orderdetails);
		viewList.add(orderid);
		viewList.add(orderimage);
		viewList.add(date);
		viewList.add(type);
		return null;
	}
	

}
