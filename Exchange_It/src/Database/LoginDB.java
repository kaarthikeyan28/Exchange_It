package Database;


import javax.swing.*;


import java.sql.Connection;
import java.sql.DriverManager;
import  java.sql.*;
public class LoginDB extends JPanel{
	String fphone,spass;
    public LoginDB(String f,String s) {
    	fphone = f;
        spass = s;
    }
    public boolean checkLogin() {
    	
    	 try {
             
             //Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Abishek00");
             PreparedStatement stat ;
             String sq = "select * from user where phone=?";
             stat=con.prepareStatement(sq);
             stat.setString(1, fphone);
             ResultSet rs = stat.executeQuery();
            if( rs.next()) {
                 String phonenumber= rs.getString(1);
                 String password = rs.getString(4);
                 con.close();
                 if ((fphone.equals(phonenumber)) && (spass.equals(password))){
                     JOptionPane.showMessageDialog(this, "Welcome");
                     
                     return true;
//                     new HomeScreen(phoneNumber);
                 }else{
                     JOptionPane.showMessageDialog(this, "Password Doesn't Match");
                     return false;
                 }}
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(this,"Account Doesn't Exist");
             e.printStackTrace();
         }
    	 
    	 return false;
    	
    }
    public static void main(String[] args){

    }
}
