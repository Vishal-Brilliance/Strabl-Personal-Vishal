package com.strabl.service.user.bdd;

import java.util.ArrayList;
import java.util.List;

public class Userdata {
	
	private Integer cardnumber;
	private String expdate;
	private Integer cvvcode;
	private String cardholdername;	
	
	public Userdata(Integer cardnumber, String expdate, Integer cvvcode, String cardholdername)
	{
		super();
		this.cardnumber = cardnumber;
		this.expdate = expdate;
		this.cvvcode = cvvcode;
		this.cardholdername = cardholdername;
	}
	public Integer getCardnumber()
	{
		return cardnumber;
	}
	public void setCardnumber(Integer cardnumber) 
	{
		this.cardnumber = cardnumber;
	}
	public String getExpdate() 
	{
		return expdate;
	}
	public void setExpdate(String expdate) 
	{
		this.expdate = expdate;
	}
	public Integer getCvvcode()
    {
		return cvvcode;
	}
	public void setCvvcode(Integer cvvcode)
	{
		this.cvvcode = cvvcode;
	}
	public String getCardholdername() 
	{
		return cardholdername;
	}
	public void setCardholdername(String cardholdername) 
	{
		this.cardholdername = cardholdername;
	}
	
	public List<String>addMultipleCard()
	{
		List<String> cardList = new ArrayList<>();
		cardList.add(cardholdername);
		cardList.add(expdate);
		return null;
		
	}
	
	
	private String search;

	public Userdata(String search) {
		super();
		this.search = search;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	public List<String>showRelatedProducts(){
		List<String> productList = new ArrayList<>();
		productList.add(search);
		return null;
		
	}
	
	
	private String emailaccount;

	public String getEmailaccount() {
		return emailaccount;
	}
	public void setEmailaccount(String emailaccount) {
		this.emailaccount = emailaccount;
	}

	public List<String>googleAccount()
	{
		List<String> accountList = new ArrayList<>();
		accountList.add(emailaccount);
		return null;
		
	}
	}
	
	




