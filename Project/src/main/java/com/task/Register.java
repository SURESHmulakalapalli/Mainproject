package com.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Register extends HttpServlet{
	
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		String Username=req.getParameter("name");
		String Password=req.getParameter("password");
		String mail=req.getParameter("Email");
		String number=req.getParameter("phoneno");
		String gender=req.getParameter("gender");
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://@localhost:3306/user","root","Root");
			
			PreparedStatement pst=con.prepareStatement("insert into user values (?,?,?,?,?)");
			
			pst.setString(1,Username);
			pst.setString(2,Password);
			pst.setString(3, mail);
			pst.setString(4, number);
			pst.setString(5, gender);
			pst.executeUpdate();
			pst.close();
			pw.println("Register success");
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.forward(req, res);
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}					
}

