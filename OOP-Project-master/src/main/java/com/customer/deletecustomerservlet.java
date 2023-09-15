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


@WebServlet("/deletecustomerservlet")
public class deletecustomerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public deletecustomerservlet() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("cusid");//cusis the name of the input type of CID in deletecustomer.jsp
		boolean isTrue;
		
		isTrue = CustomerDBUtil.deleteCustomer(id);//catch the return value of istrue variable in deletecustomer method in customerDBUtil class
		
		if (isTrue == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("customerinsert.jsp");//if delete success then user navigate to the registration page
			dispatcher.forward(request, response);
		}
		else {
			
			List<Customer> cusDetails = CustomerDBUtil.getCustomerDetails(id);
			request.setAttribute("cusDetails", cusDetails);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("useraccount.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

}
