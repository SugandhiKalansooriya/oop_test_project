package com.customer;


import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.CustomerDBUtil;


@WebServlet("/customerinsert")
public class customerinsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public customerinsert() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");//save the name that customer entered in variable
		String nic = request.getParameter("nic");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String username = request.getParameter("uid");
		String password = request.getParameter("password");
		
		boolean isTrue;//catch the return value by the variable
		
		isTrue = CustomerDBUtil.insertcustomer(name,nic, address,email, phone, username, password);
		
		if(isTrue == true) {//check the database connection success or not
			RequestDispatcher dis = request.getRequestDispatcher("Home.jsp");//navigate to Success.jsp page
			dis.forward(request, response);
		} else {
		
			RequestDispatcher dis2 = request.getRequestDispatcher("unsuccess.jsp");
			dis2.forward(request, response);
		}
	}

}
