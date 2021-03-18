package classes;

import java.util.*;

import Database.DbConnection;

import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class User{
	
	public String phoneNumber,mail,name,location;
	Connection con;
	
	public User() {
		
		
	}
	
	
	
	public User(String phone) {
		
	
		this.phoneNumber=phone;

		String query="select * from user where phone=?" ;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			con=DbConnection.getCon();
			ps=con.prepareStatement(query);
			ps.setString(1, phone);
			rs=ps.executeQuery();
			rs.next();
			this.location=rs.getString(5);
			this.mail=rs.getString(2);
			this.name=rs.getString(3);
			con.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public String returnUserLocation(String phone) {
		String query="select location from user where phone=?" ;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			con=DbConnection.getCon();
			ps=con.prepareStatement(query);
			ps.setString(1, phone);
			rs=ps.executeQuery();
			if(rs.next())
			return rs.getString(1);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public String getEmail(String sellerPhone)
	{
		this.phoneNumber = sellerPhone;
		String query="select * from user where phone=?";
		PreparedStatement ps=null;
		ResultSet rs=null;


		try {
			con=DbConnection.getCon();
			ps=con.prepareStatement(query);
			ps.setString(1,this.phoneNumber);
			rs=ps.executeQuery();
			rs.next();
			this.mail=rs.getString(2);
			con.close();
			return this.mail;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.mail;
	}
	
	
	public void deleteUser() {
		
		String query="delete from user where phone=?";
		PreparedStatement ps=null;
		ResultSet rs=null;


		try {
			con=DbConnection.getCon();
			ps=con.prepareStatement(query);
			ps.setString(1,this.phoneNumber);
			ps.executeUpdate();
			con.close();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
}