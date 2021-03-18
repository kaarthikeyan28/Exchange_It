package screens;


import javax.swing.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Database.MProduct;
import Database.Product;
import Database.Transaction;
import classes.User;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;


class RequestedProductList extends JPanel{
	
	
	String phoneNumber,email;
	JFrame f=new JFrame("Frame");
	JPanel main=new JPanel();
	String productName,pid,desc,cond;
	ArrayList<Product> products=new ArrayList<Product>();
	double cost;
	int status;
	JLabel heading=new JLabel("");
	JLabel descHeading=new JLabel("Description:");
	JTextArea descLabel=new JTextArea();
	JLabel conditionHeading=new JLabel("Condition: ");
	JTextArea conditionLabel=new JTextArea("");
	JPanel second=new JPanel();
	JPanel contactPanel=new JPanel();
	JLabel mobileLabel=new JLabel("Mobile:");
	JLabel mobileout=new JLabel("");
	JLabel emailLabel=new JLabel("E-mail:");
	JLabel emailout=new JLabel("");
	JPanel conditionPanel=new JPanel();
	JPanel descriptionPanel=new JPanel();
	JButton backButton=new JButton("Back");
	User userObj;
	JTable reqProdTable;
	JButton endT=new JButton("End Transaction");
	String transacID;
	JLabel statusLabel=new JLabel("");
	JLabel locationLabel=new JLabel("Location: ");
	JLabel locationOut=new JLabel("");
	
	void addComp() {
		
		f.add(main);
		main.add(heading);
		main.add(Box.createRigidArea(new Dimension(0,70)));
		descriptionPanel.add(descHeading);
		descriptionPanel.add(descLabel);
		main.add(descriptionPanel);
//		main.add(Box.createRigidArea(new Dimension(0,70)));
		conditionPanel.add(conditionHeading);
		conditionPanel.add(conditionLabel);
		main.add(conditionPanel);
		main.add(contactPanel);
		contactPanel.add(mobileLabel);
		contactPanel.add(mobileout);
		contactPanel.add(emailLabel);
		contactPanel.add(emailout);
		contactPanel.add(locationLabel);
		contactPanel.add(locationOut);
		second.add(statusLabel);
		if(status==0) {
			second.add(backButton);	
		}
		
		main.add(second);
		if(status==1 || status==-1) {
			second.add(endT);
		}
		
		
//		
	}
	
	
		 
		
	
	
	
	
	void setCharacterstics() {
		
		mobileout.setText(userObj.phoneNumber);
		emailout.setText(userObj.mail);
		locationOut.setText(userObj.location);
		emailLabel.setFont(new Font("Serif",Font.PLAIN,20));
		emailout.setFont(new Font("Serif",Font.PLAIN,20));
		mobileLabel.setFont(new Font("Serif",Font.PLAIN,20));
		mobileout.setFont(new Font("Serif",Font.PLAIN,20));
		locationOut.setFont(new Font("Serif",Font.PLAIN,20));
		locationLabel.setFont(new Font("Serif",Font.PLAIN,20));
		main.setBackground(Color.decode("0x121E31"));
		contactPanel.setBackground(Color.decode("0x121E31"));
		second.setBackground(Color.decode("0x121E31"));
		descriptionPanel.setBackground(Color.decode("0x121E31"));
		conditionPanel.setBackground(Color.decode("0x121E31"));
		descHeading.setForeground(Color.WHITE);
		conditionHeading.setForeground(Color.WHITE);
		heading.setForeground(Color.WHITE);
		emailLabel.setForeground(Color.WHITE);
		mobileLabel.setForeground(Color.WHITE);
		mobileout.setForeground(Color.WHITE);
		emailout.setForeground(Color.WHITE);
		conditionLabel.setForeground(Color.WHITE);
		 backButton.setBackground(Color.ORANGE);
		endT.setForeground(Color.BLACK);
		endT.setBackground(Color.ORANGE);
		 conditionLabel.setEditable(false);  
		 conditionLabel.setCursor(null);  
		 conditionLabel.setOpaque(false);  
		 conditionLabel.setFocusable(false);
		 conditionLabel.setLineWrap(true);
		 conditionLabel.setForeground(Color.WHITE);
		 conditionLabel.setWrapStyleWord(true);
		 descLabel.setForeground(Color.WHITE);
		locationLabel.setForeground(Color.WHITE);
		locationOut.setForeground(Color.WHITE);
		 descLabel.setEditable(false);  
		 descLabel.setCursor(null);  
		 descLabel.setOpaque(false);
		 descLabel.setFocusable(false);
		 descLabel.setLineWrap(true);
		 descLabel.setWrapStyleWord(true);
		conditionLabel.setPreferredSize(new Dimension(500,150));
		descLabel.setPreferredSize(new Dimension(500,100));
		heading.setText(productName);
		heading.setFont(new Font("Serif",Font.PLAIN,45));
		descHeading.setFont(new Font("Serif",Font.PLAIN,30));
		descLabel.setFont(new Font("Serif",Font.PLAIN,24));
		heading.setAlignmentX(CENTER_ALIGNMENT);
		descHeading.setAlignmentX(CENTER_ALIGNMENT);
		descLabel.setAlignmentX(CENTER_ALIGNMENT);
		descLabel.setText(desc);
		conditionLabel.setText(cond);
		conditionHeading.setFont(new Font("Serif",Font.PLAIN,30));
		conditionLabel.setFont(new Font("Serif",Font.PLAIN,24));
		conditionHeading.setAlignmentX(CENTER_ALIGNMENT);
		conditionLabel.setAlignmentX(CENTER_ALIGNMENT);
		second.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));
		contactPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		conditionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,100,10));
		descriptionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,100,10));
		if(this.status==1) {
			statusLabel.setText("Your request has been accepted");
			
		}
		else if(this.status==-1) {
			statusLabel.setText("Your request has been rejected");
			
		}
		else {
			statusLabel.setText("Waiting for response");
		}
	}
	

	
	RequestedProductList(Product product,User userObj,String phoneNumber,String transacID,String status){
		this.transacID=transacID;
		this.status=Integer.parseInt(status);
		this.phoneNumber=phoneNumber;
		this.userObj=userObj;
		this.productName=product.name;
		this.desc=product.description;
		this.pid=product.pid;
		this.cond=product.condition;
//		this.cost=product.cost;
		
		setCharacterstics();
		addComp();
		addListeners();
		main.setPreferredSize(getMaximumSize());
		f.setSize(getMaximumSize()); 	

        f.setResizable(false);
        f.show();
	}
	
	
	
	
	void addListeners() {
		
		
		
		endT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				new Transaction().setBuyerEndT(transacID);
				if(new Transaction().checkTransacEnd(transacID) && new Transaction().getStatus(transacID)==1) {
					new MProduct().deleteProduct(transacID);
				}
				else if(new Transaction().checkTransacEnd(transacID) && new Transaction().getStatus(transacID)==-1) {
					new Transaction().DeleteTransaction(transacID);
					
				}
				f.dispose();
				HomeScreen h=new HomeScreen(phoneNumber);
			}
			
		});
		
		backButton.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					f.dispose();
					RequestList obj=new RequestList(phoneNumber);
					
				}
			}
			);
		
		
		
	}
	
	
	
}