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

public class Feed extends HttpServlet{		
	
		public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
			PrintWriter pw=res.getWriter();
			res.setContentType("text/html");
			
			String fUsername=req.getParameter("uname");
			String fmail=req.getParameter("email");
			String fnumber=req.getParameter("phone");
			String Satisfy=req.getParameter("satisfy");
			String Remark=req.getParameter("msg");
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://@localhost:3306/user","root","Root");
				
				PreparedStatement pst=con.prepareStatement("insert into feedback values (?,?,?,?,?)");
				
				pst.setString(1,fUsername);
				pst.setString(2,fmail);
				pst.setString(3, fnumber);
				pst.setString(4, Satisfy);
				pst.setString(5, Remark);
				pst.executeUpdate();
				pst.close();
				pw.println("Feedback success");
				
			}
			catch(Exception e) {
				System.out.println(e);
			}
			
		}					
}



