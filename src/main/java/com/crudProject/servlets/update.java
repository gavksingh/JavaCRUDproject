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
 * Servlet implementation class update
 */
@WebServlet("/update")
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public update() {
        super();
        // TODO Auto-generated constructor stub
    }
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONArray list = new JSONArray();
		String stid= request.getParameter("empid");
		String fname = request.getParameter("FirstName");
		String lname = request.getParameter("LastName");
		String email = request.getParameter("EmailID");
		String number = request.getParameter("Contact");
		String address = request.getParameter("Address");
		String gender = request.getParameter("Gender");
		
		
		Connection con = null;
		PrintWriter out= response.getWriter();
		PreparedStatement pst;
		JSONObject obj = new JSONObject();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "gaurav");
			
			pst = con.prepareStatement("update crudp set FirstName=?, LastName=?, EmailID=?, Contact=?, Address=?, Gender=? where id=?");
			pst.setString(1, fname);
			pst.setString(2, lname);
			pst.setString(3, email);
			pst.setString(4, number);
			pst.setString(5, address);
			pst.setString(6, gender);
			pst.setString(7, stid);
			int i=pst.executeUpdate();
			if(i!=0) {
				obj.put("status", "success");
				list.add(obj);
				obj.put("message", "Record Updated Successfully");
				
			}else {
				obj.put("status", "error");
				
				obj.put("message", "Ooops!");
			}
			
			
			out.println(list.toJSONString());
			out.flush();
			
			
		}catch (Exception e) {
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
