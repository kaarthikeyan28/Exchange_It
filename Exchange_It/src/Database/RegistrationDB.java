package Database;


import javax.swing.*;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegistrationDB  extends JPanel{
    public static int flag=1;
    public RegistrationDB(String mail,String pass,String ph,String n,String Loc) {
        String email = mail;
        String phonenumber = ph;
        String name = n;
        String password = pass;
        String Location = Loc;
        System.out.println(password);
        ResultSet rs = null;
        //Scanner sc = new Scanner(System.in);
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Abishek00");
            PreparedStatement stat = con.prepareStatement("insert into user values(?,?,?,?,?)");
            stat.setString(1,phonenumber);
            stat.setString(2,email);
            stat.setString(3,name);
            stat.setString(4,password);
            stat.setString(5,Location);
            stat.executeUpdate();
            con.close();
        } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Number Already exists");
                flag = 0;
            }
        }
    public static void main(String[] args){

    }
}
