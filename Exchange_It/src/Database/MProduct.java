package Database;


import java.util.*;
import java.sql.*;

import java.lang.*;

public class MProduct {

    public String pid ;
    public String name ;
    public String status;
    public double cost;
    public String phone,proid,pname;
    public int i=0;
    Connection con;



public  MProduct() {
	
	

}

    public ResultSet getMyProduct(String phone) {
        this.phone=phone;
        String query="select * from transaction where sellerid=? and status!=-2";
        PreparedStatement ps=null;
        ResultSet rs=null;


        try {
        	con=DbConnection.getCon();
            ps=con.prepareStatement(query);
            ps.setString(1,this.phone);
            rs=ps.executeQuery();
            return rs;
            


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;


    }
    
    public ResultSet getMyRequestList(String phone) {
        this.phone=phone;
        String query="select * from transaction where buyerid=?";
        PreparedStatement ps=null;
        ResultSet rs=null;


        try {
        	con=DbConnection.getCon();
            ps=con.prepareStatement(query);
            ps.setString(1,this.phone);
            rs=ps.executeQuery();
            return rs;
            


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;


    }

    public String getProductName(String proid){
        this.proid = proid;
        System.out.print("\n"+proid);
        
        String query="select * from user_product where pid=?";
        PreparedStatement ps=null;
        ResultSet rs=null;


        try {
        	con=DbConnection.getCon();
            ps=con.prepareStatement(query);
            ps.setString(1,proid);
            rs=ps.executeQuery();
           if( rs.next()) {
            
               this.pname = rs.getString(3);
               con.close();
               return this.pname;
           }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       

            return this.pname;
    }
    
    public boolean checkForProducts(String phoneNumber) {
    	String query="select * from user_product where phone=?";
        PreparedStatement ps=null;
        ResultSet rs;


        try {
        	con=DbConnection.getCon();
            ps=con.prepareStatement(query);
            ps.setString(1,phoneNumber);
            rs=ps.executeQuery();
            if (rs.next()){
            	con.close();
            	return true;
            }
               


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     
        return false;

    	
    	
    }

    public String getPhoneNumber(String proid){
        this.proid = proid;
        String query="select * from user_product where pid=?";
        PreparedStatement ps=null;
        ResultSet rs=null;


        try {
        	con=DbConnection.getCon();
            ps=con.prepareStatement(query);
            ps.setString(1,proid);
            rs=ps.executeQuery();
            rs.next();
               this.phone = rs.getString(2);
               con.close();

               return this.phone;


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

            return this.pname;
    }

    public void deleteProduct(String transacId) {
    	
		String buyerPId,sellerPId;
		buyerPId=transacId.split(" ")[1];
		sellerPId=transacId.split(" ")[0];
		String query="delete from user_product where pid=?" ;
		String query2="delete from transaction where transaction_id=? or buyerProdId=?";
		String query4="update transaction set sellerProdId=?,status=?,transaction_id=? where sellerProdId=?";
		PreparedStatement dltBuyer=null,dltTransac=null,dltSeller=null,changeSeller=null,changeBuyer=null;
		ResultSet rs=null;

		try {
			con=DbConnection.getCon();
			dltBuyer=con.prepareStatement(query);
			dltTransac=con.prepareStatement(query2);
			dltSeller=con.prepareStatement(query);
			changeSeller=con.prepareStatement(query4);
			changeSeller.setString(1, "UA");
			changeSeller.setString(2, sellerPId);
			changeSeller.setInt(3, -2);
			changeSeller.setString(4, "UA");
//			changeBuyer=con.prepareStatement(query4);
//			changeBuyer.setString(1,"0");
//			changeBuyer.setString(2, buyerPId);
//			changeBuyer.setInt(3, -2);
			dltBuyer.setString(1,buyerPId);
			dltTransac.setString(1, transacId);
			dltTransac.setString(2,buyerPId);
			dltSeller.setString(1,sellerPId);
			dltBuyer.executeUpdate();
			dltTransac.executeUpdate();
			dltSeller.executeUpdate();
			changeSeller.executeUpdate();
//			changeBuyer.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}