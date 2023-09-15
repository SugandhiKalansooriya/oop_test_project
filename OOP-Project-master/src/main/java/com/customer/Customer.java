package com.customer;


public class Customer {//get customer details from the database
	private int CID;
    private String cusName;
    private String NIC;
    private String cusAddress;
    private String cusEmail;
    private int contactNo;
    private String UserName;
    private String Password;
	public Customer(int cID, String cusName, String nIC, String cusAddress, String cusEmail, int contactNo,
			String userName, String password) {
		super();
		this.CID = cID;
		this.cusName = cusName;
		this.NIC = nIC;
		this.cusAddress = cusAddress;
		this.cusEmail = cusEmail;
		this.contactNo = contactNo;
		this.UserName = userName;
		this.Password = password;
	}
	public int getCID() {
		return CID;
	}

	public String getCusName() {
		return cusName;
	}
	
	public String getNIC() {
		return NIC;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public String getCusEmail() {
		return cusEmail;
	}
	
	public int getContactNo() {
		return contactNo;
	}

	public String getUserName() {
		return UserName;
	}
	
	public String getPassword() {
		return Password;
	}
	
	
	

}
