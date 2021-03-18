package Database;

import java.sql.*;


public class Transaction{
	String requestId,recieverId,requestProdId,recieverProdId;
	int status;
	
	
	public Transaction(String rId,String recieverId,String rProdId,String recieverProdId) {
		this.requestId=rId;
		this.recieverId=recieverId;
		this.recieverProdId=recieverProdId;
		this.requestProdId=rProdId;
		
	}
	
	public Transaction() {
		
	}
	public void setTransactionDetails() {
		String query="insert into transaction values(?,?,?,?,?,?,?,?);";
		PreparedStatement ps=null;
		
		try {
			Connection con=DbConnection.getCon();
			ps=con.prepareStatement(query);
			ps.setString(1,this.requestId);
			ps.setString(2, this.recieverId);
			ps.setString(3, "0");
			ps.setString(4, this.recieverProdId+" "+this.requestProdId);
			ps.setString(5, this.requestProdId);
			ps.setString(6, this.recieverProdId);
			ps.setString(7, "0");
			ps.setString(8, "0");
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	 public boolean checkExistingTransactions(String requesterProdId,String recieverProdId) {
		 
		 String query="select transaction_id from transaction ";
			PreparedStatement ps=null;
			
			try {
				Connection con=DbConnection.getCon();
				ps=con.prepareStatement(query);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()){
					if(rs.getString(1).equals(recieverProdId+" "+requesterProdId)) {
						return true;
					}
					
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return false;
		
	}
	 
	 public int getStatus(String transacId) {
		 
		 String query="select status from transaction where transaction_id=?";
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			try {
				Connection con=DbConnection.getCon();
				ps=con.prepareStatement(query);
				ps.setString(1, transacId);
				rs=ps.executeQuery();
				rs.next();
				int status=rs.getInt(1);
				con.close();
				return status;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			return -1;
			
		 
			
		 
	 }
	 
	 public boolean SameProductTransaction(String sellerId) {
		 
		 String query="select sellerEnd from transaction where sellerid=?";
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			try {
				Connection con=DbConnection.getCon();
				ps=con.prepareStatement(query);
				ps.setString(1, sellerId);
				rs=ps.executeQuery();
				while(rs.next()) {
					if(rs.getInt(1)==1) {
						con.close();
						return true;
					}
				}
				con.close();
				return false;
					
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return false;
		 
		 
	 }
	 
	 public boolean checkAlreadyReq(String transac) {
		 
		 String tId=transac.split(" ")[1]+" "+transac.split(" ")[0];
		 
		 String query="select * from transaction where transaction_id=?";
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			try {
				Connection con=DbConnection.getCon();
				ps=con.prepareStatement(query);
				ps.setString(1, tId);
				rs=ps.executeQuery();
				if(rs.next()) {
					con.close();
					return true;
				}
				else {
					con.close();
					return false;
				}
					
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return false;
			
		 
	 }
	 public void delTransac(String phone){
		 
		 
		 String query="delete from transaction where buyerid=? and transaction_id=? ";
			PreparedStatement ps=null;
			
			try {
				Connection con=DbConnection.getCon();
				ps=con.prepareStatement(query);
				ps.setString(1, phone);
				ps.setString(2, "UA");
				ps.executeUpdate();
				con.close();
			
					
					
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	 }
	 
	 
	 public void setSellerEnd(String transacId) {
		 
		 String query="update transaction set sellerEnd=?,status=? where transaction_id=?";
			PreparedStatement ps=null;
			
			try {
				Connection con=DbConnection.getCon();
				ps=con.prepareStatement(query);
				ps.setInt(1, 1);
				ps.setInt(2, -1);
				ps.setString(3, transacId);
				ps.executeUpdate();
				con.close();
			
					
					
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 
		 
	 }
	 public void setBuyerEndT(String transacId) {
		 
		 String query="update transaction set buyerEnd=1 where transaction_id=?";
			PreparedStatement ps=null;
			
			try {
				Connection con=DbConnection.getCon();
				ps=con.prepareStatement(query);
				ps.setString(1, transacId);
				ps.executeUpdate();
				con.close();
			
					
					
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		 
	 }
	 
	 public void DeleteTransaction(String transacId) {
		 
		 String query="delete from transaction where transaction_id=?";
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			try {
				Connection con=DbConnection.getCon();
				ps=con.prepareStatement(query);
				ps.setString(1, transacId);
				ps.executeUpdate();
				con.close();

			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		 
	 }
	 
	 public void setTransAndSellerEnd(String transacID) {
		 
		 String sellerProdId=transacID.split(" ")[0];
		 String query="update transaction set status=?,sellerEnd=? where transaction_id=?";
		 String query2="update transaction set status=?,sellerProdId=?,transaction_id=? where sellerProdId=?";
			PreparedStatement ps=null;
			PreparedStatement ps2=null;
			ResultSet rs=null;
			System.out.print("\ncame here");
			
			try {
				Connection con=DbConnection.getCon();
				ps=con.prepareStatement(query);
				ps2=con.prepareStatement(query2);
				ps.setInt(1, 1);
				ps.setInt(2, 1);
				ps2.setInt(1, -2);
				ps2.setString(2, "UA");
				ps2.setString(3, "UA");
				ps2.setString(4, sellerProdId);
				ps.setString(3, transacID);
				ps.execute();
				ps2.executeUpdate();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 
		 
		 
	 }
	 
	 public boolean checkTransacEnd(String transacId) {
		 
		 String query="select buyerEnd,sellerEnd from transaction where transaction_id=?";
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			try {
				Connection con=DbConnection.getCon();
				ps=con.prepareStatement(query);
				ps.setString(1, transacId);
				rs=ps.executeQuery();
				rs.next();
				int buyerEnd=rs.getInt(1),sellerEnd=rs.getInt(2);
				if(buyerEnd==1 && sellerEnd==1) {
					con.close();
					return true;
					
				}			
				else {
					con.close();
					return false;
				}
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return false;
			
		 
	 }
	 
	 
	 
	 
	 
	 
	
	 
	 
	
}