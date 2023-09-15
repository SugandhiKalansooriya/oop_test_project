package com.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.CustomerDBUtil;


@WebServlet("/nicLoginServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ViewServlet() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//if login unsuccess, show javascript error and redirect to the login page
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				
				String password = request.getParameter("password");//catch the username that customer entered
				
				boolean isTrue;
				
				isTrue = CustomerDBUtil.validatenic(password);
				
				if(isTrue == true) {
					
					 List<Customer> cusDetails = CustomerDBUtil.getCustomerDetailspass(password);
				     request.setAttribute("cusDetails", cusDetails);
					
					//navigate to another page
					RequestDispatcher dis = request.getRequestDispatcher("useraccount.jsp");
					dis.forward(request, response);
				}
				else
				{
					out.println("<script type='text/javascript'>");
					out.print("alert('Your password is incorrect');");
					out.println("location = 'Home.jsp'");
					out.println("</script>");
				}
	}

}
