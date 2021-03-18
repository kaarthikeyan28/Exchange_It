package screens;


import javax.swing.*;


import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Database.MProduct;
import Database.Product;
import classes.User;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;




public class HomeScreen extends JPanel {
	String phoneNumber;
	JFrame f=new JFrame("Frame");
	double income,totalSpent,totalSaved;
	String[] strCategories = new String[] { "Furniture", "Books", "Gaming", "Shirts", "Shoes", "Stationary", "Gym", "Jeans",
	         "Sports","SmartPhones","Laptops" };
	 JTable productTable;
	  
    // creating panels 
    JPanel p1 = new JPanel(); 
    JPanel p = new JPanel(); 
    JComboBox categoriesDropDown=new JComboBox(strCategories);
    
    // Buttons for options
    JButton b1=new JButton("Request List");
    JButton b2=new JButton("Add/Delete Product");
    JButton b3=new JButton("Log Out");
    JLabel headingLabel=new JLabel("Search for products");
 
    // creating split panes
    JSplitPane sl = new JSplitPane(SwingConstants.VERTICAL, p1, p); 
    User userObj;
    JScrollPane tableScrollPane;
    JButton deleteAcc=new JButton("Delete Account");
	
	void removeElements(JPanel panel) {
		
		panel.removeAll();
		
	}
	void addListener() {
		
		deleteAcc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				userObj.deleteUser();
				
			}
		});
		
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				LoginFrame m=new LoginFrame();
				
			}
		});
		
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
//				new Testing().sendMail("abishekganesh4@gmail.com");
				new RequestList(userObj.phoneNumber);
				
			}
			
		});
	}
	
	void createP() {
		
		
		
		p.add(headingLabel);
		p.add(Box.createRigidArea(new Dimension(0,20)));
		p.add(categoriesDropDown);
		p.add(Box.createRigidArea(new Dimension(0,20)));
		createTable();
	}
	
	void createTable() {
		ResultSet rs=new Product().getProductsByCategory(categoriesDropDown.getSelectedItem().toString(),phoneNumber);
		String row[]= {"pname","location","pid"};
		DefaultTableModel model=new DefaultTableModel();
		model.addColumn("Product Name");
		model.addColumn("Location");
		model.addColumn("Product ID(click to know more)");
	    productTable=new JTable(model) {
	    	public boolean isCellEditable(int row,int column) {
	    		return false;
	    	}
	    };

		tableScrollPane=new JScrollPane(productTable);
	    try {
			while(rs.next()) {
				row[0]=rs.getString(3);
				row[1]=new User().returnUserLocation(rs.getString(2));
				row[2]=rs.getString(1);
				
				model.addRow(row);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    productTable.addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	        	if(new MProduct().checkForProducts(userObj.phoneNumber)) {
	        		int row = productTable.rowAtPoint(evt.getPoint());
	        		int col = productTable.columnAtPoint(evt.getPoint());
	        		if (row >= 0 && col == 2) {
	        			String pid=productTable.getModel().getValueAt(row,col).toString();
	        			String productName=productTable.getModel().getValueAt(row, col-2).toString();
	        			Product product=new Product(pid,productName,categoriesDropDown.getSelectedItem().toString());

	        			f.dispose();
	        			ProductDetail pd=new ProductDetail(product,userObj);

	            }
	        	}
	        	else {
	        		JOptionPane.showMessageDialog(f, "Seems Like you have'nt saved any products\nadd products to continue","Alert",JOptionPane.WARNING_MESSAGE);
	        	}
	        	
	        }
	        
	        
	    });

		tableScrollPane.getViewport().setBackground(Color.decode("0x121E31"));
		p.add(tableScrollPane);
	    
		
	}
	
	void addElements() {
		

		
		// adding buttons to first pane 
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(deleteAcc);
        createP();
        
        // adding panels wherever required 
        f.add(sl); 
        
        
        
	}
	
	
	void setCharacterstics() {
		

		headingLabel.setFont(new Font("Serif",Font.PLAIN,40));
		headingLabel.setAlignmentX(CENTER_ALIGNMENT);
		categoriesDropDown.setAlignmentX(CENTER_ALIGNMENT);
		p.setBackground(Color.decode("0x121E31"));
		p1.setBackground(Color.decode("0x121E31"));
		sl.setBackground(Color.decode("0x121E31"));
		categoriesDropDown.setBackground(Color.ORANGE);
		headingLabel.setForeground(Color.WHITE);
		headingLabel.setFont(new Font("Serif",Font.PLAIN,30));
		tableScrollPane.getViewport().setBackground(Color.decode("0x121E31"));
		categoriesDropDown.setForeground(Color.BLACK);
	       b1.setPreferredSize(new Dimension(180,80));
	       b2.setPreferredSize(new Dimension(180,80));
	       b3.setPreferredSize(new Dimension(180,80));
	       deleteAcc.setPreferredSize(new Dimension(180,80));
	       deleteAcc.setBackground(Color.ORANGE);
	       
	       b1.setBackground(Color.ORANGE);
	       b2.setBackground(Color.ORANGE);
	       b3.setBackground(Color.ORANGE);
      // set Orientation for slider 
	        sl.setOrientation(SwingConstants.VERTICAL); 
	        sl.setEnabled(false);
	        Dimension d=getMaximumSize();
	        sl.setDividerLocation(200);

	        //setting layout for panes
	        p1.setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
	        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));

	        // set the size of frame 
	        f.setSize(d.width,d.height); 	

	        f.setResizable(false);

	}
	

	
	HomeScreen(String phone){
		phoneNumber=phone;
		userObj=new User(phoneNumber);
		
 

	        categoriesDropDown.setEditable(false);
	        categoriesDropDown.setMaximumSize(categoriesDropDown.getPreferredSize());
	        categoriesDropDown.setAlignmentY(LEFT_ALIGNMENT);
	        addListener();
	        categoriesDropDown.addItemListener(new ItemListener() {
	    	   
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange()==e.SELECTED) {
						p.removeAll();
						createP();
						f.revalidate();
						f.repaint();
					}
				}
	        });
	        
	        addElements();
	        setCharacterstics();
	        f.show();
	        b2.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent ae) {
	        		f.dispose();
	        		AddDel obj=new AddDel(userObj);
	        	}
	        });

	  
	}
	
	public static void main(String[] args) {  
//		new HomeScreen("9500909092");  
	}
}