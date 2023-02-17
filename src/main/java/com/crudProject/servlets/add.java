package com.crudProject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * Servlet implementation class add
 */
@WebServlet("/add")
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONArray list = new JSONArray();

		String FirstName = request.getParameter("fname");
		String LastName = request.getParameter("lname");
		String EmailID = request.getParameter("email");
		String Contact = request.getParameter("number");
		String Address = request.getParameter("address");
		String Gender = request.getParameter("gender");

		Connection con = null;
		PrintWriter out= response.getWriter();
//		PreparedStatement pt;
//		ResultSet rs;

		JSONObject obj = new JSONObject();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "gaurav");
			PreparedStatement pt =con.prepareStatement("insert into crudp(FirstName,LastName,EmailID,Contact,Address,Gender) values(?,?,?,?,?,?)");
			pt.setString(1, FirstName);
			pt.setString(2, LastName);
			pt.setString(3, EmailID);
			pt.setString(4, Contact);
			pt.setString(5, Address);
			pt.setString(6, Gender);
//			System.out.println("Data is successfully inserted!");
			int rowCount=pt.executeUpdate();
			obj.put("name", "success");
			list.add(obj);
			
			out.println(list.toJSONString());
			out.flush();
			
			if(rowCount>0) {
				request.setAttribute("status", "success");
				response.setHeader("Refresh","1;index.jsp");
			
				
			}
			else {
				request.setAttribute("status", "failed");
				System.out.println("Enter every field");
			}
//			

		} catch (Exception e) {
//			System.out.print(e);
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
