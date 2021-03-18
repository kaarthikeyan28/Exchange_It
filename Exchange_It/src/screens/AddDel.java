package screens;
//end transaction on clicked should delete everything everywhere
//delete product deletion process
//buyer delete product after accepting for a trade before seller clicks end transaction
import javax.swing.*;

import javax.swing.border.TitledBorder;
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



public class AddDel extends JPanel{
	String phoneNumber;
	JFrame frame=new JFrame();
//    Container container1 = getContentPane();
    JLabel ProductNameLabel= new JLabel("Product Name");
    JLabel CostLabel= new JLabel("Cost");
    JLabel CategoriesLabel= new JLabel("Categories");
    JLabel DeleteLabel= new JLabel("Delete");
    JTextArea Description=new JTextArea();
    JTextArea Condition=new JTextArea();
    JTextField ProductField = new JTextField();
    JTextField CostField = new JTextField();
    JTextField deleteField=new JTextField("enter pid to delete");
    JLabel DescriptionLabel= new JLabel("Description");
    JLabel ConditionLabel= new JLabel("Condition");
    JButton AddButton = new JButton("Add");
    JButton DeleteButton = new JButton("Delete");
    String Categories[]={"Furniture", "Books", "Gaming", "Shirts", "Shoes", "Stationary", "Gym", "Jeans",
	         "Sports","SmartPhones","Laptops"};        
    JComboBox Categoriesdrop=new JComboBox(Categories);
//    String Product[]={"Product"};        
//    JComboBox ProductPick=new JComboBox(Product);
    JPanel addPanel=new JPanel();
    JPanel secondPanel=new JPanel();
    JPanel tablePanel=new JPanel();
    JPanel deletePanel=new JPanel();
    JSplitPane s1=new JSplitPane(SwingConstants.HORIZONTAL,addPanel,secondPanel);
    JSplitPane s2=new JSplitPane(SwingConstants.VERTICAL,tablePanel,deletePanel);
    JButton backButton=new JButton("Back");
    JLabel heading=new JLabel("Add or Delete Products");
    
    User obj;
    
    
    public void setColor(){
    	ProductNameLabel.setForeground(Color.WHITE);
    	CostLabel.setForeground(Color.WHITE);
    	CategoriesLabel.setForeground(Color.WHITE);
    	DeleteLabel.setForeground(Color.WHITE);
    	DescriptionLabel.setForeground(Color.WHITE);
    	ConditionLabel.setForeground(Color.WHITE);
    	ProductNameLabel.setForeground(Color.WHITE);
    	ProductNameLabel.setForeground(Color.WHITE);
    	ProductNameLabel.setForeground(Color.WHITE);
    	ProductNameLabel.setForeground(Color.WHITE);
    	Categoriesdrop.setBackground(Color.ORANGE);
    	Categoriesdrop.setForeground(Color.BLACK);
    	secondPanel.setBackground(Color.decode("0x121E31"));
    	addPanel.setBackground(Color.decode("0x121E31"));
    	tablePanel.setBackground(Color.decode("0x121E31"));
    	deletePanel.setBackground(Color.decode("0x121E31"));
    	AddButton.setBackground(Color.ORANGE);
    	backButton.setBackground(Color.ORANGE);
    	DeleteButton.setBackground(Color.ORANGE);
    	heading.setFont(new Font("Serif",Font.PLAIN,28));
    	heading.setForeground(Color.WHITE);
        ProductNameLabel.setForeground(Color.WHITE);
        CostLabel.setForeground(Color.WHITE);
        CategoriesLabel.setForeground(Color.WHITE);
        DeleteLabel.setForeground(Color.WHITE);
        DescriptionLabel.setForeground(Color.WHITE);
        ConditionLabel.setForeground(Color.WHITE);
        ProductField.setForeground(Color.BLACK);
        CostField.setForeground(Color.BLACK);
        AddButton.setForeground(Color.WHITE);
        DeleteButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.WHITE);
//        container1.setBackground(Color.decode("#C4C7CC"));
    }


    AddDel(User userObj) {
    	obj=userObj;
    	Condition.setLineWrap(true);
		Condition.setWrapStyleWord(true);
		Description.setLineWrap(true);
		Description.setWrapStyleWord(true);
    	this.phoneNumber=phoneNumber;
        setLayoutManager();
        setLocationAndSize();
        setColor();
        addComponentsToContainer();
        addActionListener();
        createTable();
        
        frame.setTitle("Add or Del");
        frame.add(s1);
        frame.setVisible(true);
//        frame.setBounds(10, 10, 450, 450);
        frame.pack();
//        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
    public void setLayoutManager() {
//        container1.setLayout(null); 
        addPanel.setLayout(null);
        s1.setEnabled(false);
        s1.setDividerLocation(500);
        s2.setDividerLocation(700);
        s2.setEnabled(false);
        deletePanel.setLayout(new FlowLayout( FlowLayout.CENTER,500,30));
        
    }
    public void setLocationAndSize() {
        ProductNameLabel.setBounds(250, 50, 100, 100);
        CostLabel.setBounds(730, 50, 100, 100);
        heading.setBounds(685,10,300,100);
        CategoriesLabel.setBounds(1200, 50, 100, 100);
        DeleteLabel.setPreferredSize(DeleteLabel.getPreferredSize());
        ProductField.setBounds(250, 120, 150, 30);
        CostField.setBounds(730, 120, 150, 30);
        AddButton.setBounds(648, 390,150, 30);
        backButton.setBounds(828,390,150,30);
        DeleteButton.setBounds(1200, 600, 150, 30);
        Categoriesdrop.setBounds(1200, 120, 150, 30);
//        ProductPick.setBounds(1200,550, 150, 30);
        Description.setBounds(450,230, 200, 150);
        Condition.setBounds(970,230, 200, 150);
        DescriptionLabel.setBounds(450,150, 100, 100);
        ConditionLabel.setBounds(970,150, 100, 100);
        tablePanel.setPreferredSize(new Dimension(700,415));
        deletePanel.setPreferredSize(new Dimension(700,415));
        deleteField.setPreferredSize(new Dimension(200,35));
       
    }
    
   
    
    public void addComponentsToContainer() { 
    	
    	
//    	tablePanel.add(new JLabel("Table"));;
        TitledBorder title=BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Your Products", TitledBorder.CENTER, TitledBorder.TOP);
    	title.setTitleColor(Color.WHITE);
    	tablePanel.setBorder(title);
        tablePanel.setLayout(new BoxLayout(tablePanel,BoxLayout.X_AXIS));
    	secondPanel.add(s2);
    	addPanel.add(heading);
    	backButton.setForeground(Color.BLACK);
    	AddButton.setForeground(Color.BLACK);
    	DeleteButton.setForeground(Color.BLACK);
    	
    	
//    	secondPanel.add(deletePanel);
        addPanel.add(ProductNameLabel);
        addPanel.add(CategoriesLabel);
        addPanel.add(CostLabel);
        deletePanel.add(DeleteLabel);
        deletePanel.add(deleteField);
        addPanel.add(ProductField);
        addPanel.add(CostField);
        addPanel.add(Description);
        addPanel.add(Condition);
        addPanel.add(DescriptionLabel);
        addPanel.add(ConditionLabel);
        addPanel.add(AddButton);
        deletePanel.add(DeleteButton);
        addPanel.add(Categoriesdrop);
        addPanel.add(backButton);
//        addPanel.add(ProductPick);
    }
    
    void addActionListener() {
    	
    	AddButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent ae) {
    			String pid=new Product().checkAssignPid(obj.phoneNumber);
    			if(pid.equals("UA")) {
    				JOptionPane.showMessageDialog(frame,"You can only have five active products registered\nPlease to delete products to add more","alert",JOptionPane.WARNING_MESSAGE);
    				
    			}
    			if(Description.getText()=="" || Condition.getText()=="" || ProductField.getText()=="" || CostField.getText()=="" ) {
    				JOptionPane.showMessageDialog(frame,"Please fill all fields correcty","alert",JOptionPane.WARNING_MESSAGE);
    			}
    			if(!pid.equals("UA")) {
    			new Product().addToProduct(pid,obj.phoneNumber,ProductField.getText(),(String)Categoriesdrop.getSelectedItem(),obj.mail);
    			new Product().addToProductDetail(pid,Description.getText(),Condition.getText(),Double.parseDouble(CostField.getText()));
    			tablePanel.removeAll();
    			createTable();
    			}
    		}
    		
    	});
    	
    	DeleteButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent ae) {
    			
    			if(deleteField.getText().length()<11 || deleteField.getText().length()>11)
    				deleteField.setText("enter valid product pid");
    			
    			else
    				new Product().deleteFromProduct(deleteField.getText());
    			tablePanel.removeAll();
    			createTable();
    		}
    	});
    	
    	backButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent ae) {
    			
    			frame.dispose();
    			HomeScreen h=new HomeScreen(obj.phoneNumber);
    			
    			
    		}
    	});
    }
    
    void createTable() {
    	
    	ResultSet rs=new Product().getTableData(obj.phoneNumber);
    	System.out.print(obj.phoneNumber);
    	DefaultTableModel model=new DefaultTableModel();
    	
    	model.addColumn("Product Name");
    	model.addColumn("Category");
    	model.addColumn("Product ID");
    	String row[]= {"name","category","pid"};
    	JTable prodTable=new JTable(model) {
    		public boolean isCellEditable(int row, int column) {
    			return false;
    		}
    	};
    	JScrollPane tableScrollPane=new JScrollPane(prodTable);
    	try {
			while(rs.next()) {
				row[0]=rs.getString(3);
				row[1]=rs.getString(4);
				row[2]=rs.getString(1);
				model.addRow(row);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	tableScrollPane.getViewport().setBackground(Color.decode("0x121E31"));
    	tablePanel.add(tableScrollPane);
    	frame.revalidate();
    	frame.repaint();
    	
    	
    	
    }

    public static void main(String[] a)
    {
       
        

    }
    
    }
