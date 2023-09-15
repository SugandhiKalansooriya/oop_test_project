package Model;
import java.util.List;


import com.customer.Customer;

import DBConnectin.DBConnect;


import java.util.ArrayList;
import java.sql.Connection; 
import java.sql.Statement; 
import java.sql.ResultSet;

public class CustomerDBUtil { 
	public static boolean isSuccess;
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	public static boolean validate(String userName, String password){/*First validate customer using username and password after 
	get customer details as a list from databse and assign details to the customer class variables*/
		
		//validate
		try {
			
			con = DBConnect.getConnection();//create connection(connection inside try catch is checking errors in db connection)
			stmt = con.createStatement();
			
		
			String sql = "select * from customer where UserName='"+userName+"' and Password='"+password+"'";//sql query to select the suitable user and get details
			rs = stmt.executeQuery(sql);//run the sql query(Using exceptions in sql)
			
			if(rs.next()) {//check the username and password correct or wrong
				isSuccess = true;	
			}
			else {
				isSuccess = false;
			}
			
		}
		catch(Exception e){ //catch the error
			e.printStackTrace();//print the error
			
		}
		
		return isSuccess;
		
	}
	public static List<Customer> getCustomerDetails2(String userName, String password){
		ArrayList<Customer> customer = new ArrayList<>();
		
		try {
			con = DBConnect.getConnection();
			stmt = con.createStatement();
			
			String sql = "select * from customer where UserName='"+userName+"' and Password='"+password+"'";//sql query to select the suitable user and get details
			rs = stmt.executeQuery(sql);//run the sql query
			
			while(rs.next()) {
				int CID = rs.getInt(1);//if username and password valid take details of that customer to this class
				String cusName = rs.getString(2);
				String NIC = rs.getString(3);
				String cusAddress = rs.getString(4);
				String cusEmail = rs.getString(5);
				int contactNo = rs.getInt(6);
				String UserName = rs.getString(7);
				String Password = rs.getString(8);
				
				Customer cus = new Customer(CID,cusName,NIC,cusAddress,cusEmail,contactNo,UserName,Password);
				
				customer.add(cus);
			}
			
			
		}
		catch(Exception e){
			
		}
		
		return customer;
	}
	public static boolean validatenic(String password){/*First validate customer using nic after 
		get customer details as a list from databse and assign details to the customer class variables*/
			
			
			
			
			
			//validate password entered in view jsp
			try {
				
				con = DBConnect.getConnection();
				stmt = con.createStatement();
				
			
				String sql = "select * from customer where Password='"+password+"' ";//sql query to select the suitable user and get details
				rs = stmt.executeQuery(sql);//run the sql query
				
				if(rs.next()) {//check the username and password correct or wrong
					isSuccess = true;	
				}
				else {
					isSuccess = false;
				}
				
			}
			catch(Exception e){ 
				e.printStackTrace();
				
			}
			
			return isSuccess;
			
		}
		public static List<Customer> getCustomerDetailspass(String password){
			ArrayList<Customer> customer = new ArrayList<>();
			
			try {
				con = DBConnect.getConnection();
				stmt = con.createStatement();
				
				String sql = "select * from customer where Password='"+password+"'";//sql query to select the suitable user and get details
				rs = stmt.executeQuery(sql);//run the sql query
				
				while(rs.next()) {
					int CID = rs.getInt(1);//if username and password valid take details of that customer to this class
					String cusName = rs.getString(2);
					String NIC = rs.getString(3);
					String cusAddress = rs.getString(4);
					String cusEmail = rs.getString(5);
					int contactNo = rs.getInt(6);
					String UserName = rs.getString(7);
					String Password = rs.getString(8);
					
					Customer cus = new Customer(CID,cusName,NIC,cusAddress,cusEmail,contactNo,UserName,Password);
					
					customer.add(cus);
				}
				
				
			}
			catch(Exception e){
				
			}
			
			return customer;
		}
	//insert data
	public static boolean insertcustomer(String name,String nic,String address, String email, String phone, String username, String password)  {
		/*connect with database and insert data into database
	that saved in customerinsert servlet*/
		
		boolean isSuccess = false;
		
		
		try {//try the content inside block
			
			con = DBConnect.getConnection();
			stmt = con.createStatement();
			
			String sql = "insert into customer values (0,'"+name+"',' "+nic+ "',' "+address+ "','"+email+"','"+phone+"','"+username+"','"+password+"')";//sql query
    		int rs = stmt.executeUpdate(sql);//store executeUpdate value in integer variable
    		//executeUpdate statement return 2 values as 0 and 1
    		//if value 0 unsuccess and if value 1 Success
    		if(rs > 0) {
    			isSuccess = true;
    		} else {
    			isSuccess = false;
    		}
    		
    	}
    	catch (Exception e) {//catch if there is an error
    		e.printStackTrace();//print that error
    	}
		
		
		
		return isSuccess;	
		
	}
	//update data
	public static boolean updatecustomer(String id, String name, String nic, String address, String email, String phone, String username, String password) {//parameters are the variables in updatecustomerServlet class
		
		boolean isSuccess = false;
try {
    		
    		con = DBConnect.getConnection();
    		stmt = con.createStatement();
    		String sql = "update customer set cusName='"+name+"',NIC='"+nic+"',cusAddress='"+address+"',cusEmail='"+email+"',contactNo='"+phone+"',UserName='"+username+"',Password='"+password+"'"
    				+ "where CID='"+id+"'";
    		int rs = stmt.executeUpdate(sql);
    		
    		if(rs > 0) {
    			isSuccess = true;//rs=1
    		}
    		else {
    			isSuccess = false;//rs=0 
    		}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
		
		return isSuccess;	
		
	}
	//retrieve data
	public static List<Customer> getCustomerDetails(String id){//list of customer details
		
		int convertedID = Integer.parseInt(id);//convert string value into integer value
		
		ArrayList<Customer> cus = new ArrayList<>();
		
try {
    		stmt = con.createStatement();
    		String sql = "select * from customer where CID='"+convertedID+"'";
    		rs = stmt.executeQuery(sql);
    		
    		while(rs.next()) {
    			int CID= rs.getInt(1);//if username and password valid take details of that customer to this class
				String cusName = rs.getString(2);
				String NIC = rs.getString(3);
				String cusAddress = rs.getString(4);
				String cusEmail = rs.getString(5);
				int contactNo = rs.getInt(6);
				String UserName = rs.getString(7);
				String Password = rs.getString(8);
    			//pass the values to the object in a Customer class through a constructor
				Customer c = new Customer(CID,cusName,NIC,cusAddress,cusEmail,contactNo,UserName,Password);//parameterize constructor
    			cus.add(c);//pass the customer object to arraylist object
    		}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}	
		
		return cus;
	}
	
	public static boolean deleteCustomer(String id) {
		
int convId = Integer.parseInt(id);
boolean isSuccess = false;
    	
    	try {
    		
    		con = DBConnect.getConnection();
    		stmt = con.createStatement();
    		String sql = "delete from customer where CID='"+convId+"'";
    		int r = stmt.executeUpdate(sql);//executeUpdate method is use for insert, update, delete
    		
    		if (r > 0) {
    			isSuccess = true;
    		}
    		else {
    			isSuccess = false;
    		}
    		
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
		
		
		return isSuccess;
	}
	

}
