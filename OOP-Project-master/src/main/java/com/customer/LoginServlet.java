
package com.customer;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.CustomerDBUtil;

import javax.servlet.RequestDispatcher;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public LoginServlet() {
        super();
      
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();//write any form of data the html to the response
		response.setContentType("text/html");
		
		String userName = request.getParameter("username");//catch the username that customer entered
		String password = request.getParameter("password");//catch the password that customer entered
		boolean isTrue;
		
		isTrue = CustomerDBUtil.validate(userName,password);
		
		if(isTrue == true) {
			
			
			//navigate to Home page page
			RequestDispatcher dis = request.getRequestDispatcher("Home.jsp");
			dis.forward(request, response);
		}
		else//if login unsuccess, show javascript error and redirect to the login page
		{
			out.println("<script type='text/javascript'>");//frontend error to identify error
			out.print("alert('Your Username or Password is incorrect');");
			out.println("location = 'login.jsp'");
			out.println("</script>");
		}
		
	}

}
