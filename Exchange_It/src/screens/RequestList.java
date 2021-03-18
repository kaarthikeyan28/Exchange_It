package screens;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Database.MProduct;
import Database.Product;
import classes.User;



public class RequestList extends JFrame{



    JFrame frame = new JFrame("EXPERIMENT");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();

    String phoneNumber;
    JButton backButton=new JButton("Back");
    JTable listTable;
    JLabel knowMoreLabel=new JLabel("click on status to know more");
    JLabel knowMoreLabel1=new JLabel("click on status to know more");
    JTable table;

	ArrayList<Integer> status=new ArrayList<Integer>();


	
	void setCharacterstics() {
		panel1.setBackground(Color.decode("0x121E31"));
		panel2.setBackground(Color.decode("0x121E31"));
		knowMoreLabel.setForeground(Color.WHITE);
		knowMoreLabel1.setForeground(Color.WHITE);
		knowMoreLabel.setFont(new Font("Serial",Font.PLAIN,20));
		knowMoreLabel1.setFont(new Font("Serial",Font.PLAIN,20));
		
		backButton.setBackground(Color.ORANGE);
		backButton.setForeground(Color.BLACK);

	}


    public RequestList(String phone)
    {
    	this.phoneNumber=phone;
        setMyProducts();
       setMyRequestList();
       setCharacterstics();
        setFrame();
    }

    public void setFrame(){

        //JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.panel3,this.panel4);
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,this.panel1,this.panel2);

        splitPane.setEnabled(false);

        splitPane.setResizeWeight(0.5);
        backButton.addActionListener(new ActionListener() {
        	

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomeScreen h=new HomeScreen(phoneNumber);
				
			}
        });
        this.frame.setExtendedState(this.frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        //this.panel2.add(splitPane1);
        this.frame.add(splitPane);
        addListenerToTable();
    }



    public void setMyProducts() {
    	ArrayList<String> productId=new ArrayList<String>();
    	ArrayList<String> exProductId=new ArrayList<String>();
    	ArrayList<Integer> status=new ArrayList<Integer>();
    	
    	ArrayList<String> productName=new ArrayList<String>();
    	
    	TitledBorder title=BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "MY PRODUCTS", TitledBorder.CENTER, TitledBorder.TOP);
        title.setTitleColor(Color.WHITE);
    	this.panel1.setBorder(title);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,1000,10));
        ResultSet rs=new MProduct().getMyProduct(phoneNumber);
       
//        System.out.print(phoneNumber);
//        String[] header = { "S.NO", "NAME", "PRODUCT ID","EXCHANGE_ID","STATUS" };
        DefaultTableModel model=new DefaultTableModel();
        model.addColumn("PRODUCT_NAME");
        model.addColumn("PRODUCT_ID");
        model.addColumn("EXCHANGE_PRODUCT_ID");
        model.addColumn("STATUS");
        
        table = new JTable(model) {
        	public boolean isCellEditable(int row,int column) {
        		return false;
        	}
        };
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400,250));
        panel1.add(knowMoreLabel);
        this.panel1.add(backButton);
     
        try {	
			while(rs.next()) {
				
				productId.add(rs.getString(6));
				exProductId.add(rs.getString(5));
				status.add(rs.getInt(3));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(String s:productId) {
        
        	productName.add(new MProduct().getProductName(s));
        }
        
        for(int i  = 0 ;i<productId.size();i++) {
        	String[] row = {productName.get(i),productId.get(i),exProductId.get(i),Integer.toString(status.get(i))};
        	model.addRow(row);
        }
        
        scrollPane.getViewport().setBackground(Color.decode("0x121E31"));
        this.panel1.add(scrollPane);
        
    }


    public void setMyRequestList()
    {
    	ArrayList<String> productId=new ArrayList<String>();
    	ArrayList<String> contact=new ArrayList<String>();
    	status=new ArrayList<Integer>();
    	ArrayList<String> buyerProductId=new ArrayList<String>();
    	ArrayList<String> productName=new ArrayList<String>();
    	TitledBorder title=BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "MY PRODUCTS", TitledBorder.CENTER, TitledBorder.TOP);
        title.setTitleColor(Color.WHITE);
        panel2.setBorder(title);
//        this.panel2.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "YOUR REQUESTED PRODUCTS", TitledBorder.CENTER, TitledBorder.TOP));
        ResultSet rs=new MProduct().getMyRequestList(phoneNumber);
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER,1000,10));
//      String[] header = { "S.NO", "NAME", "PRODUCT ID","EXCHANGE_ID","STATUS" };
      DefaultTableModel model=new DefaultTableModel();
      model.addColumn("PRODUCT_NAME");
      model.addColumn("PRODUCT_ID");
      model.addColumn("CONTACT");
      model.addColumn("EX PROD ID");
      model.addColumn("STATUS");
  
      
      listTable = new JTable(model) {
      	public boolean isCellEditable(int row,int column) {
      		return false;
      	}
      };
      panel2.add(knowMoreLabel1);
      JScrollPane tableScrollPane=new JScrollPane(listTable);
      tableScrollPane.setPreferredSize(new Dimension(400,300));
      
//      listTable.setPreferredSize(new Dimension());
   
      try {
			while(rs.next()) {
				
				productId.add(rs.getString(6));
				contact.add(rs.getString(2));
				status.add(rs.getInt(3));
				buyerProductId.add(rs.getString(5));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      for(String s:productId) {
    	 
      	productName.add(new MProduct().getProductName(s));
      
    	  
      }
      
      for(int i  = 0 ;i<productId.size();i++) {
    	  String statusString="";
    	 
    	  if(status.get(i)==1) {
    		  statusString="accepted";
    	  }
    	  else if(status.get(i)==0) {
    		  statusString="waiting for response";
    		  
    	  }
    	  else if(status.get(i)==-1){
    		  statusString="rejected";
    	  }
    	  else {
    		  statusString="unavailable";
    	  }
      	String[] row = {productName.get(i),productId.get(i),contact.get(i),buyerProductId.get(i),statusString};
      	
      	model.addRow(row);
      	
      }
      
      tableScrollPane.getViewport().setBackground(Color.decode("0x121E31"));
      panel2.add(tableScrollPane);
    
    }
    
    void addListenerToTable() {
    	
    	listTable.addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            int row = listTable.rowAtPoint(evt.getPoint());
	            int col = listTable.columnAtPoint(evt.getPoint());
	            if (row >= 0 && col == 4) {
//	                String status=listTable.getModel().getValueAt(row,col).toString();
	                String exchangeProductId =listTable.getModel().getValueAt(row, col-1).toString();
	                String contact=listTable.getModel().getValueAt(row, col-2).toString();
	                String productID=listTable.getModel().getValueAt(row, col-3).toString();
	                String productName=" ";
	                try {
	                productName=listTable.getModel().getValueAt(row, col-4).toString();
	                Product product=new Product(productName,productID);
	                User user=new User(contact);
	                RequestedProductList pd=new RequestedProductList(product,user,phoneNumber,productID+" "+exchangeProductId,Integer.toString(status.get(row)));

	               
	                }
	                catch(Exception e) {
	                	productName="UA";
	                	DelReqProd d=new DelReqProd(phoneNumber);
	                }
	                

	                frame.dispose();
	                
	            }
	        }
	    });
    	
    	table.addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            int row = table.rowAtPoint(evt.getPoint());
	            int col = table.columnAtPoint(evt.getPoint());
	            if (row >= 0 && col == 3) {
	                String status=table.getModel().getValueAt(row,col).toString();
	                String exchangeProductId =table.getModel().getValueAt(row, col-1).toString();
	                //String contact=listTable.getModel().getValueAt(row, col-2).toString();
	                String productID=table.getModel().getValueAt(row, col-2).toString();
	                String productName=table.getModel().getValueAt(row, col-3).toString();
	                System.out.print("seller-"+productID+"buyer-"+exchangeProductId);
	                AcceptPage ap=new AcceptPage(productID,exchangeProductId);
//	                Product product=new Product(productName,productID);
//	                User user=new User(contact);

	                frame.dispose();
//	                RequestedProductList pd=new RequestedProductList(product,user,phoneNumber,productID+" "+exchangeProductId,status);

	            }
	        }
	    });
    	
    	
    }
    
    




    public static void main(String[] args) {
        
    }
}