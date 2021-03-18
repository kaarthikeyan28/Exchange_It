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

public class DelReqProd extends JPanel{
	
	JFrame f=new JFrame("whattt");
	JPanel main=new JPanel();
	JLabel heading=new JLabel("The product you have requested has either been traded or deleted by the owner");
	JButton end=new JButton("End");
	public DelReqProd(String phone) {
		main.setLayout(new FlowLayout(FlowLayout.CENTER,100,100));
		
		main.setBackground(Color.decode("0x121E31"));
		end.setBackground(Color.ORANGE);
		end.setForeground(Color.BLACK);
		heading.setFont(new Font("Serif",Font.PLAIN,38));
		main.add(heading);
		main.add(end);
		heading.setAlignmentX(CENTER_ALIGNMENT);
		
		main.setPreferredSize(getMaximumSize());
		f.setSize(getMaximumSize()); 	
		f.add(main);

        f.setResizable(false);
        f.show();
        end.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		new Transaction().delTransac(phone);
        		f.dispose();
        		HomeScreen h=new HomeScreen(phone);
        	}
        });
	
	}
	
	
	
}