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


class AcceptPage extends JPanel{
	
	
	JFrame f=new JFrame("Frame");
	JPanel main=new JPanel();
//	String productName,pid,desc,cond;
	ArrayList<Product> products=new ArrayList<Product>();
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
	String buyerPID,sellerPID,buyerDesc,buyerCond,sellerDesc,sellerCond,buyerEmail,sellerEmail,buyerID,sellerID,buyerPName,sellerPName;
	Product buyerP,sellerP;
	User buyer,seller;
	JLabel locationLabel=new JLabel("Location:");
	JLabel locationOut=new JLabel("");
	JButton acceptButton=new JButton("Accept");
	JButton rejectButton=new JButton("Reject");
	String sellerMsg,buyerMsg;
	
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
//		second.add(backButton);
		second.add(acceptButton);
		second.add(rejectButton);
		main.add(second);
				
	}
	
	
	void setCharacterstics() {
		
		mobileout.setText(buyer.phoneNumber);
		emailout.setText(buyer.mail);
		locationOut.setText(buyer.location);
		emailLabel.setFont(new Font("Serif",Font.PLAIN,20));
		emailout.setFont(new Font("Serif",Font.PLAIN,20));
		mobileLabel.setFont(new Font("Serif",Font.PLAIN,20));
		mobileout.setFont(new Font("Serif",Font.PLAIN,20));
		locationOut.setFont(new Font("Serif",Font.PLAIN,20));
		emailLabel.setForeground(Color.WHITE);
		mobileLabel.setForeground(Color.WHITE);
		mobileout.setForeground(Color.WHITE);
		emailout.setForeground(Color.WHITE);
		 conditionLabel.setForeground(Color.WHITE);
		 backButton.setBackground(Color.ORANGE);
		 acceptButton.setBackground(Color.ORANGE);
		 rejectButton.setBackground(Color.ORANGE);
		 locationLabel.setFont(new Font("Serif",Font.PLAIN,20));
		 locationLabel.setForeground(Color.WHITE);
		 locationOut.setForeground(Color.WHITE);
		descLabel.setForeground(Color.WHITE);
		main.setBackground(Color.decode("0x121E31"));
		contactPanel.setBackground(Color.decode("0x121E31"));
		second.setBackground(Color.decode("0x121E31"));
		descriptionPanel.setBackground(Color.decode("0x121E31"));
		conditionPanel.setBackground(Color.decode("0x121E31"));
		descHeading.setForeground(Color.WHITE);
		conditionHeading.setForeground(Color.WHITE);
		heading.setForeground(Color.WHITE);
		 conditionLabel.setEditable(false);  
		 conditionLabel.setCursor(null);  
		 conditionLabel.setOpaque(false);  
		 conditionLabel.setFocusable(false);
		 conditionLabel.setLineWrap(true);
		 conditionLabel.setWrapStyleWord(true);
		 descLabel.setEditable(false);  
		 descLabel.setCursor(null);  
		 descLabel.setOpaque(false);
		 descLabel.setFocusable(false);
		 descLabel.setLineWrap(true);
		 descLabel.setWrapStyleWord(true);
		conditionLabel.setPreferredSize(new Dimension(500,150));
		descLabel.setPreferredSize(new Dimension(500,100));
		heading.setText(buyer.name+" has requested "+sellerP.name+" in exchange for "+buyerP.name);
		heading.setFont(new Font("Serif",Font.PLAIN,45));
		descHeading.setFont(new Font("Serif",Font.PLAIN,30));
		descLabel.setFont(new Font("Serif",Font.PLAIN,24));
		heading.setAlignmentX(CENTER_ALIGNMENT);
		descHeading.setAlignmentX(CENTER_ALIGNMENT);
		descLabel.setAlignmentX(CENTER_ALIGNMENT);
		descLabel.setText(buyerP.description);
		conditionLabel.setText(buyerP.condition);
		conditionHeading.setFont(new Font("Serif",Font.PLAIN,30));
		conditionLabel.setFont(new Font("Serif",Font.PLAIN,24));
		conditionHeading.setAlignmentX(CENTER_ALIGNMENT);
		conditionLabel.setAlignmentX(CENTER_ALIGNMENT);
		second.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));
		contactPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		conditionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,100,10));
		descriptionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,100,10));
		
	}
	
	AcceptPage(String sellerPId,String buyerPId){
		this.sellerPID=sellerPId;
		this.buyerPID=buyerPId;
		this.buyerID=new Product().returnPhoneMail(buyerPID)[0];
		this.buyerEmail=new Product().returnPhoneMail(buyerPID)[1];
		this.sellerID=new Product().returnPhoneMail(sellerPID)[0];
		this.sellerEmail=new Product().returnPhoneMail(buyerPID)[1];
		this.buyerPName=new MProduct().getProductName(buyerPID);
		this.sellerPName=new MProduct().getProductName(sellerPID);
		this.buyerP=new Product(buyerPName,buyerPID);
		this.sellerP=new Product(sellerPName,sellerPID);
		this.buyer=new User(buyerID);
		this.seller=new User(sellerID);
		 sellerMsg="\nTrader Name:"+buyer.name+"\nBuyer Location:"+buyer.location+"\nbuyer mail:"+buyer.mail+"\nBuyer Phone:"+buyer.phoneNumber+"\nProduct:"+buyerP.name+"\nDescription:"+buyerP.description+"\nCondition:"+buyerP.condition+"\n\nTransaction Completed";
		buyerMsg="\nTrader Name:"+seller.name+"\nBuyer Location:"+seller.location+"\nbuyer mail:"+seller.mail+"\nBuyer Phone:"+seller.phoneNumber+"\nProduct:"+sellerP.name+"\nDescription:"+sellerP.description+"\nCondition:"+sellerP.condition+"\n\nTransaction Completed";

		
		
		
		setCharacterstics();
		addComp();
		addListeners();
		main.setPreferredSize(getMaximumSize());
		f.setSize(getMaximumSize()); 	

        f.setResizable(false);
        f.show();
	}
	
	void addListeners() {
	
		acceptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(new Transaction().SameProductTransaction(seller.phoneNumber)) {
					JOptionPane.showMessageDialog( f, "You have already accepted for a different transaction with this product.");
				}
				else {
				new Transaction().setTransAndSellerEnd(sellerP.pid+" "+buyerP.pid);
				new Mailling().sendMail(seller.mail,sellerMsg);
				new Mailling().sendMail(buyer.mail, buyerMsg);
				f.dispose();
				HomeScreen h=new HomeScreen(seller.phoneNumber);
					}
				}
		});
		
		rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				new Transaction().setSellerEnd(sellerP.pid+" "+buyerP.pid);
				f.dispose();
				HomeScreen h=new HomeScreen(seller.phoneNumber);
			}
		});
		
		
	}
	
	
	
}