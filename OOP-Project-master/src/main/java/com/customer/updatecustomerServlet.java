package com.customer;

import java.io.IOException;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.CustomerDBUtil;


@WebServlet("/updatecustomerServlet")
public class updatecustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public updatecustomerServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//save the details that entered by customer
		String id = request.getParameter("cusid");//cusid is the name of the input type of CID in updatecustomer.jsp
		String name = request.getParameter("name");
		String nic = request.getParameter("nic");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String username = request.getParameter("uname");
		String password = request.getParameter("pass");
		
		boolean isTrue;
		
		isTrue = CustomerDBUtil.updatecustomer( id, name,  nic,  address,  email,  phone,  username,  password);//parameters used in updatecustomer method in customerDBUtil class
		
if(isTrue == true) {
	        List<Customer> cusDetails = CustomerDBUtil.getCustomerDetails(id);
	        request.setAttribute("cusDetails", cusDetails);
			
			
			RequestDispatcher dis = request.getRequestDispatcher("useraccount.jsp");/*if customer details successfully updated
			navigate to the user account page with updated details */
			dis.forward(request, response);
		}
		else {
			List<Customer> cusDetails = CustomerDBUtil.getCustomerDetails(id);
			request.setAttribute("cusDetails", cusDetails);
			
			
			RequestDispatcher dis = request.getRequestDispatcher("useraccount.jsp");/*if customer details successfully updated
			navigate to the user account page with current details */
			dis.forward(request, response);
		}
		
		
	}

}
