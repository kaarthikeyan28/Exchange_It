package screens;

import javax.swing.*;

import Database.RegistrationDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration extends JPanel implements ActionListener {
        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        JLabel title;
        JLabel name;
        JLabel email;
        JLabel number;
        JLabel password;
        JLabel passReset;
        JLabel Location;
        JTextField LocationText;
        JTextField emailText;
        JTextField nameText;
        JTextField numberText;
        JPasswordField passwordTextbox;
        JPasswordField passResetTextbox;
        JCheckBox showPassword;
        JButton button;
        Color color = Color.decode("0x121E31");//color code

    Registration(){
        //Registration------------------------------------------------------------------------------------------------------

            title = new JLabel("REGISTRATION");
            title.setForeground(Color.ORANGE);
            title.setFont(new Font("SERIF", Font.BOLD, 42));
            title.setBounds(770, 230, 400, 50);
            frame.add(title);

        //Name------------------------------------------------------------------------------------------------------------

            name = new JLabel("Name:");
            name.setFont(new Font("Times New Roman", Font.BOLD, 20));
            name.setBounds(740, 330, 100, 30);
            name.setForeground(Color.white);
            frame.add(name);
            //nameText
            nameText = new JTextField();
            nameText.setBounds(875, 330, 200, 30);
            nameText.setFont(new Font("Arial", Font.BOLD, 18));
            frame.add(nameText);

        //eMAIL---------------------------------------------------------------------------------------------------------
            email = new JLabel("E-mail:");
            email.setFont(new Font("Times New Roman", Font.BOLD, 20));
            email.setBounds(740, 390, 100, 30);
            email.setForeground(Color.white);
            frame.add(email);
            //emailtext
            emailText = new JTextField();
            emailText.setBounds(875, 390, 200, 30);
            emailText.setFont(new Font("Arial",Font.BOLD,18));
            frame.add(emailText);

        //location------------------------------------------------------------------------------------------------------

            Location = new JLabel("Location:");
            Location.setFont(new Font("Times New Roman", Font.BOLD, 20));
            Location.setBounds(740, 450, 300, 30);
            Location.setForeground(Color.white);
            frame.add(Location);
            //Locationtext
            LocationText = new JTextField();
            LocationText.setBounds(875, 450, 200, 30);
            LocationText.setFont(new Font("Arial", Font.BOLD, 18));
            frame.add(LocationText);

        //Phone number--------------------------------------------------------------------------------------------------

            number = new JLabel("Phone Number:");
            number.setFont(new Font("Times New Roman", Font.BOLD, 20));
            number.setBounds(740, 510, 300, 30);
            number.setForeground(Color.white);
            frame.add(number);
            //Numbertext
            numberText = new JTextField();
            numberText.setBounds(875, 510, 200, 30);
            numberText.setFont(new Font("Arial", Font.BOLD, 18));
            frame.add(numberText);


        //Password------------------------------------------------------------------------------------------------------

            password = new JLabel("Password:");
            password.setFont(new Font("Times New Roman", Font.BOLD, 20));
            password.setBounds(740, 570, 100, 30);
            password.setForeground(Color.white);
            frame.add(password);
            //passwordtext
            passwordTextbox = new JPasswordField();
            passwordTextbox.setBounds(875, 570, 200, 30);
            passwordTextbox.setFont(new Font("Arial", Font.BOLD, 18));
            frame.add(passwordTextbox);

        //re enter password---------------------------------------------------------------------------------------------

            passReset = new JLabel("Re-enter:");
            passReset.setFont(new Font("Times New Roman", Font.BOLD, 20));
            passReset.setBounds(740, 630, 100, 30);
            passReset.setForeground(Color.white);
            frame.add(passReset);
            //passResettextbox
            passResetTextbox = new JPasswordField();
            passResetTextbox.setBounds(875, 630, 200, 30);
            passResetTextbox.setFont(new Font("Arial", Font.BOLD, 18));
            frame.add(passResetTextbox);

        //show password-----------------------------------------------------------------------------------------------------

            showPassword = new JCheckBox("Show Password");
            showPassword.setBounds(960, 670, 200, 20);
            showPassword.setForeground(Color.white);
            showPassword.setBackground(color);
            showPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            showPassword.addActionListener(this);
            frame.add(showPassword);

        //Register button---------------------------------------------------------------------------------------------------

            button = new JButton("Register");
            button.setBounds(905, 710, 100, 40);
            button.setBackground(Color.ORANGE);
            button.setForeground(Color.BLACK);
            button.addActionListener(this);
            frame.add(button);

        //Frame Layouts-----------------------------------------------------------------------------------------------------

            frame.getContentPane().setBackground(color);//background color
            frame.setTitle("EXCHANGE IT");//title of the program
            frame.setForeground(Color.ORANGE);
            frame.setLocationByPlatform(true);
            Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\hp\\Desktop\\Bootathon\\ex.png");//logo location
            frame.setIconImage(icon);
            frame.setSize(400, 500);//400 width and 500 height
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);//using no layout managers
            frame.setVisible(true);//making the frame visible
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
     
            @Override
            public void actionPerformed (ActionEvent e) {
                
                //show password checkbox
                if (e.getSource() == showPassword){
                    if (showPassword.isSelected()){
                        passwordTextbox.setEchoChar((char) 0);
                        passResetTextbox.setEchoChar((char) 0);
                    }else{
                        passwordTextbox.setEchoChar('*');
                        passResetTextbox.setEchoChar('*');
                    }
                }
                if (e.getSource() == button){
                    //namenotvalid
                    String demail=emailText.getText();
                    String pass=passwordTextbox.getText();
                    String ph =numberText.getText();
                    String n = nameText.getText();
                    String Loc = LocationText.getText();
                    //Flags
                    Boolean nameF=true;
                    if (!(nameText.getText().matches("^[a-zA-Z\\s]*"))){
                        JOptionPane.showMessageDialog(this,"Name should be in Alphabets !");
                        nameF=false;
                    }
                    //PLease fill all
                    else if (nameText.getText().length() <= 0 ||  passwordTextbox.getText().length() <= 0 || passResetTextbox.getText().length() <= 0 || emailText.getText().length() <=0 || LocationText.getText().length()<=0) {
                        JOptionPane.showMessageDialog(this, " Please fill all fields ");
                        nameF=false;
                    }
                    //numbernotvalid
                    else if (!(numberText.getText().matches("(0/91)?[6-9][0-9]{9}"))){
                        JOptionPane.showMessageDialog(this," Required Number !");
                        nameF=false;
                    }
                    //email
                    else if (!(emailText.getText().matches("^(\\w)+@[a-z]+.com$"))){
                        JOptionPane.showMessageDialog(this,"Email Not valid");
                        nameF=false;
                    }
                    //passwordnotvalid
                    else if(!(passwordTextbox.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"))){
                        JOptionPane.showMessageDialog(this," Required Characters and Numbers");
                        nameF=false;
                    }
                    //passwordmatches
                    else if (!(passwordTextbox.getText().equals(passResetTextbox.getText()))){
                        JOptionPane.showMessageDialog(this,"Password Not matches !");
                        nameF=false;
                    }
                    else if(!(RegistrationDB.flag==1)){
                        nameF=false;
                    }
                    else {
                        if (nameF == true) {
                            JOptionPane.showMessageDialog(this, "Account Created Successfully !");
                            new RegistrationDB(demail,pass,ph,n,Loc);
                            frame.dispose();
                            new LoginFrame();
                        }
                    }
                    //JOptionPane.showMessageDialog(this,"Account Created Successfully");
            }//Register button
    }//action Event

    public static void main(String[] args){
     new Registration();
    }
    }
