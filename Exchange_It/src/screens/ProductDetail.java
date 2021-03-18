package screens;


import javax.swing.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Database.Product;
import Database.Transaction;
import classes.User;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;


class ProductDetail extends JPanel{
	
	
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
	JLabel costHeading=new JLabel("Cost");
	JLabel costLabel=new JLabel();
	JComboBox productSelectionDropDown;
	JPanel second=new JPanel();
	JLabel selection=new JLabel("Product you would like to exchange:");
	JPanel contactPanel=new JPanel();
	JLabel mobileLabel=new JLabel("Mobile:");
	JLabel mobileout=new JLabel("");
	JLabel emailLabel=new JLabel("E-mail:");
	JLabel emailout=new JLabel("");
	JPanel conditionPanel=new JPanel();
	JPanel descriptionPanel=new JPanel();
	JButton requestButton=new JButton("Request");
	JButton backButton=new JButton("Back");
	User userObj;
	JLabel reqLabel=new JLabel("This person has already requested this product from you");
	
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
		second.add(selection);
		second.add(productSelectionDropDown);
		second.add(requestButton);
		second.add(backButton);
		main.add(second);
		
		
//		
	}
	
	
	
	void setCharacterstics() {
		
		String[] ar=new Product().returnPhoneMail(pid);
		mobileout.setText(ar[0]);
		emailout.setText(ar[1]);
		main.setBackground(Color.decode("0x121E31"));
		contactPanel.setBackground(Color.decode("0x121E31"));
		second.setBackground(Color.decode("0x121E31"));
		descriptionPanel.setBackground(Color.decode("0x121E31"));
		conditionPanel.setBackground(Color.decode("0x121E31"));
		descHeading.setForeground(Color.WHITE);
		conditionHeading.setForeground(Color.WHITE);
		heading.setForeground(Color.WHITE);
		
		emailLabel.setFont(new Font("Serif",Font.PLAIN,20));
		emailout.setFont(new Font("Serif",Font.PLAIN,20));
		mobileLabel.setFont(new Font("Serif",Font.PLAIN,20));
		mobileout.setFont(new Font("Serif",Font.PLAIN,20));
		emailLabel.setForeground(Color.WHITE);
		mobileLabel.setForeground(Color.WHITE);
		mobileout.setForeground(Color.WHITE);
		emailout.setForeground(Color.WHITE);
		productSelectionDropDown.setPreferredSize(productSelectionDropDown.getPreferredSize());
		productSelectionDropDown.setAlignmentX(CENTER_ALIGNMENT);
		productSelectionDropDown.setBackground(Color.ORANGE);
		productSelectionDropDown.setForeground(Color.BLACK);
		selection.setForeground(Color.WHITE);
		 conditionLabel.setEditable(false);  
		 conditionLabel.setCursor(null);  
		 conditionLabel.setOpaque(false);  
		 conditionLabel.setFocusable(false);
		 conditionLabel.setLineWrap(true);
		 conditionLabel.setWrapStyleWord(true);
		 conditionLabel.setForeground(Color.WHITE);
		 productSelectionDropDown.setAlignmentX(CENTER_ALIGNMENT);
		 requestButton.setBackground(Color.ORANGE);
		 backButton.setBackground(Color.ORANGE);
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
	}
	
	
	void checkTransactions() {
//		String selectedDropDown=productSelectionDropDown.getSelectedItem().toString();
//		String[] arr=selectedDropDown.split(" ");
//		String dropDownProdId=arr[arr.length-1];
//		System.out.print(dropDownProdId+pid);
		int index=productSelectionDropDown.getSelectedIndex();
		if(index==-1) {
			System.out.print("you have not added any products for exchanging please add");
		}
		else {
		String dropDownProdId=products.get(index).pid;
		System.out.print("sssssssssssss"+dropDownProdId);
		if(new Transaction().checkExistingTransactions(dropDownProdId, pid)) {
			
			requestButton.setText("Requested");
			
		}
		else {
			requestButton.setText("Request");
		}
		}
	}
	

	 
	
void addProductArray() {
		ResultSet rs=new Product().getUserProducts(userObj.phoneNumber);
		try {
		while(rs.next()) {
			
				products.add(new Product(rs.getString(3),rs.getString(1)));
				this.email=rs.getString(2);
		
				
				
		}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		productSelectionDropDown=new JComboBox();
		for(int i=0;i<products.size();i++) {
			productSelectionDropDown.addItem(products.get(i).name);
		}

		}
	
	
	ProductDetail(Product product,User userObj){
		this.userObj=userObj;
		this.productName=product.name;
		this.desc=product.description;
		this.pid=product.pid;
		this.cond=product.condition;
//		this.cost=product.cost;
		this.status=product.status;
		addProductArray();
		addComp();
		checkTransactions();
		addListeners();
		main.setPreferredSize(getMaximumSize());
		setCharacterstics();
		 descLabel.setForeground(Color.WHITE);
		f.setSize(getMaximumSize()); 	

        f.setResizable(false);
        f.show();
	}
	
	void addListeners() {
		
		
		
		productSelectionDropDown.addItemListener(new ItemListener() {
			@Override 
			public void itemStateChanged(ItemEvent ie) {
				
				checkTransactions();
				
			}
		});
		
		backButton.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					f.dispose();
					HomeScreen obj=new HomeScreen(userObj.phoneNumber);
					
				}
			}
			);
		
		requestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				if(requestButton.getText().equals("Request")) {
					int index=productSelectionDropDown.getSelectedIndex();
					String dropDownProdId=products.get(index).pid;
					if(new Transaction().checkAlreadyReq(pid+" "+dropDownProdId)) {
						JOptionPane.showMessageDialog(f,"this person has requested the same product from you!!\n check your Request List.");
					}
					else {
				new Product().changeStatus(pid);
				
				Transaction t=new Transaction(userObj.phoneNumber,new Product().returnPhoneMail(pid)[0],dropDownProdId,pid);
				t.setTransactionDetails();
				requestButton.setText("Requested");
					}
				}
				
			}
		});
		
	}
	
	
	
}