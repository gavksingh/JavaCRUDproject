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
 * Servlet implementation class delete
 */
@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public delete() {
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
		JSONObject obj = new JSONObject();
//		String id = request.getParameter("id");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "gaurav");
			String id = request.getParameter("id");
			pst = con.prepareStatement("delete from crudp where id = ?");
			pst.setString(1, id);
			int i=pst.executeUpdate();
			if(i!=0) {
				obj.put("status", "success");
				list.add(obj);
				obj.put("message", "Record Deleted Successfully");
				
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
