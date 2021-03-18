package Database;


import java.util.*;
import java.sql.*;

import java.lang.*;

public class Product{
	
	public String pid,description,condition,name,category;
	public double cost;
	public int status;
	Connection con;
	
	public Product() {
		try {
			con=DbConnection.getCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public Product(String name,String pid) {
		
		try {
			con=DbConnection.getCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pid=pid;
		this.name=name;
		String query="select * from product_detail where pid=?";
		PreparedStatement ps=null;
		ResultSet rs=null;

		
		try {
			ps=con.prepareStatement(query);
			ps.setString(1, this.pid);
			rs=ps.executeQuery();
			rs.next();
			this.description=rs.getString(2);
			this.condition=rs.getString(3);
			this.cost=rs.getDouble(4);
			con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

public Product(String pid,String name,String category) {
	
	try {
		con=DbConnection.getCon();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.pid=pid;
	this.name=name;
	this.category=category;
	String query="select * from product_detail where pid=?";
	PreparedStatement ps=null;
	ResultSet rs=null;

	
	try {
		ps=con.prepareStatement(query);
		ps.setString(1, this.pid);
		rs=ps.executeQuery();
		rs.next();
		this.description=rs.getString(2);
		this.condition=rs.getString(3);
		this.cost=rs.getDouble(4);
		this.status=rs.getInt(5);
		con.close();
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
	
	public ResultSet getProductsByCategory(String category,String phoneNumber) {
		String query="select * from user_product where category=? and phone!=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
//			Connection con=DbConnection.getCon();
			ps=con.prepareStatement(query);
			ps.setString(1, category);
			ps.setString(2, phoneNumber);
			rs=ps.executeQuery();
			return rs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	

	
	
	public ResultSet getUserProducts(String phoneNumber){
		
		String query="select * from user_product where phone=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=con.prepareStatement(query);
			ps.setString(1,phoneNumber);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	
	
	

	
	public void changeStatus(String pid) {
		String query="update product_detail set status=1 where pid=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=con.prepareStatement(query);
			ps.setString(1, pid);
			ps.executeUpdate();
			con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public String[] returnPhoneMail(String pid) {
		String query="select * from user_product where pid=? ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		String[] arr= {"phone","email"};

		try {
			ps=con.prepareStatement(query);
			ps.setString(1, pid);
			rs=ps.executeQuery();
			rs.next();
			arr[0]=rs.getString(2);
			arr[1]=rs.getString(5);
			rs.close();
			con.close();
			return arr;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr
				;
	}
	
	public String checkAssignPid(String phone) {
		
		String query="select pid from user_product where phone=? ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		String[] pidArray= {phone+"1",phone+"2",phone+"3",phone+"4",phone+"5"};
		try {
			ps=con.prepareStatement(query);
			ps.setString(1, phone);
			rs=ps.executeQuery();
			while(rs.next()) {
				int flag=0;
				String pid=rs.getString(1);
				for(int i=0;i<pidArray.length;i++) {
					if(pid.equals(pidArray[i])) {
						pidArray[i]="0";
						break;
					}
					
					
				}	
			}
			for(String s:pidArray) {
				if(!s.equals("0")) {
					
					return s;
				}
				
			}
			con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "UA";
	}
	
	public void addToProduct(String pid,String phone,String pname,String category,String mail) {
		
		String query="insert into user_product values(?,?,?,?,?)" ;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=con.prepareStatement(query);
			ps.setString(1, pid);
			ps.setString(2, phone);
			ps.setString(3, pname);
			ps.setString(4, category);
			ps.setString(5, mail);
			ps.executeUpdate();
			con.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addToProductDetail(String pid,String desc,String condition,double cost) {

		String query="insert into product_detail values(?,?,?,?,?)" ;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=con.prepareStatement(query);
			ps.setString(1, pid);
			ps.setString(2, desc);
			ps.setString(3, condition);
			ps.setString(4, Double.toString(cost));
			ps.setString(5, "0");
			ps.executeUpdate();
			con.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public ResultSet getTableData(String phone) {
		
		String query="select * from user_product where phone=?" ;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			ps=con.prepareStatement(query);
			ps.setString(1, phone);
			rs=ps.executeQuery();
			return rs;
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	
	
	public void deleteFromProduct(String pid) {
		
		String query="delete from user_product where pid=?" ;
		String query2="delete from transaction where buyerProdId=?";
		String query3="update transaction set sellerProdId=?,status=?,transaction_id=? where sellerProdId=?";
		PreparedStatement ps=null,ps2=null,ps3=null;
		ResultSet rs=null;

		try {
			ps=con.prepareStatement(query);
			ps2=con.prepareStatement(query2);
			ps3=con.prepareStatement(query3);
			ps.setString(1,pid);
			ps2.setString(1, pid);
			ps3.setString(1, "UA");
			ps3.setInt(2, -2);
			ps3.setString(3, "UA");
			ps3.setString(4, pid);
			ps.executeUpdate();
			ps2.executeUpdate();
			ps3.executeUpdate();
			con.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
	}
	
	
}