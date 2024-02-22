package com.task;
import javax.swing.*;

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
import javax.swing.JOptionPane;


public class Home extends HttpServlet{
		

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		String Username=req.getParameter("name");
		String Password=req.getParameter("password");
	
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://@localhost:3306/user","root","Root");
			
			ResultSet r=null;
			PreparedStatement pst=con.prepareStatement("select * from user where name=?");
			pst.setString(1, Username);
			r=pst.executeQuery();
			
			String cname="";
			String cpwd = "";
			while(r.next()) {
				cname = r.getString(1);
				cpwd = r.getString(2);
			}
			if(Username.equals(cname)&& Password.equals(cpwd)) {
				RequestDispatcher rd=req.getRequestDispatcher("welcome.html");
				rd.forward(req, res);
				
			}
			else {
				
				
				RequestDispatcher rd=req.getRequestDispatcher("login.html");
				rd.forward(req, res);
			}
		
		}
		catch(Exception e) {
			RequestDispatcher rd=req.getRequestDispatcher("index.html");
			rd.forward(req, res);
		}
		
	}
}
