package com.crudProject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
 * Servlet implementation class edit_return
 */
@WebServlet("/edit_return")
public class edit_return extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public edit_return() {
        super();
        // TODO Auto-generated constructor stub
    }


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONArray list = new JSONArray();
		Connection con = null;
		PrintWriter out= response.getWriter();
		PreparedStatement pst;
		ResultSet rs;
		
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "gaurav");
			String id = request.getParameter("id");
			pst = con.prepareStatement("select id,FirstName,LastName,EmailID,Contact,Address,Gender from crudp where id = ?");
			pst.setString(1, id);
			rs = pst.executeQuery();
			
			if(rs.next()==true)
			{
			    String id1 = rs.getString(1);
			    String fname = rs.getString(2);
			    String lname = rs.getString(3);
			    String email = rs.getString(4);
			    String number = rs.getString(5);
			    String address = rs.getString(6);
			    String gender = rs.getString(7);
			    
			    
			     JSONObject obj = new JSONObject();
			    
			     obj.put("id",id1);
			     obj.put("fname",fname);
			     obj.put("lname",lname);
			     obj.put("email",email);
			     obj.put("number",number);
			     obj.put("address",address);
			     obj.put("gender",gender);
			     list.add(obj);
			    
			    
			}
			 
			out.print(list.toJSONString());
			out.flush();
		}
		 catch (Exception e) {
//				System.out.print(e);
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
