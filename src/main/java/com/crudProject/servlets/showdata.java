package com.crudProject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * Servlet implementation class showdata
 */
@WebServlet("/showdata")
public class showdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONArray list = new JSONArray();
		Connection con = null;
		PrintWriter out= response.getWriter();
		
//		ResultSet rs = null;
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "gaurav");
			Statement st= con.createStatement();
			ResultSet rs=st.executeQuery("select * from crudp");
		 
			while(rs.next())
			{
				JSONObject obj = new JSONObject();
			    String id = rs.getString("id");
			    String fname = rs.getString("FirstName");
			    String lname = rs.getString("LastName");
			    String email = rs.getString("EmailID");
			    String number = rs.getString("Contact");
			    String address = rs.getString("Address");
			    String gender = rs.getString("Gender");
			    obj.put("fname", fname);
			    obj.put("lname", lname);
			    obj.put("email", email);
			    obj.put("number", number);
			    obj.put("address", address);
			    obj.put("gender", gender);
			    obj.put("id", id);
			    list.add(obj);
			    
			}
			out.print(list.toJSONString());
			out.flush();
		
		    
		    
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
//		out.print(list.toJSONString());
//		out.flush();

	}
}
