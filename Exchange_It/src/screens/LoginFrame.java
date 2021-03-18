package screens;



import javax.swing.*;

import Database.LoginDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener{
    Container container = getContentPane();
    JLabel title = new JLabel("LOGIN");
    JLabel phoneNumberLabel = new JLabel("Phone Number:");
    JLabel passwordLabel = new JLabel("Password:");
    JTextField phoneTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JButton signup = new JButton("SIGN UP");
    JCheckBox showPassword = new JCheckBox("Show Password");

    LoginFrame(){
        setLayoutManager();
        setLocationAndSize();
        setColor();
        setFont();
        addComponentsToContainer();
        addActionEvent();
        this.setTitle("Exchange It");
        this.setVisible(true);
        this.setBounds(10, 10, 450, 450);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\hp\\Desktop\\Bootathon\\ex.png");//logo location
        this.setIconImage(icon);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        title.setBounds(860,300,400,50);
        signup.setBounds(940,620,150,30);
        phoneNumberLabel.setBounds(750, 400, 150, 30);
        passwordLabel.setBounds(750, 470, 150, 30);
        phoneTextField.setBounds(900, 400, 200, 30);
        passwordField.setBounds(900, 470, 200, 30);
        showPassword.setBounds(980, 540, 120, 30);
        loginButton.setBounds(750, 620, 150, 30);
        resetButton.setBounds(750, 540, 150, 30);
    }

    public void setFont(){
        title.setFont(new Font("SERIF", Font.BOLD, 42));
        signup.setFont(new Font("Times New Roman",Font.BOLD,20));
        loginButton.setFont(new Font("Times New Roman",Font.BOLD,20));
        phoneTextField.setFont(new Font("Arial",Font.PLAIN,20));
        passwordField.setFont(new Font("Arial",Font.PLAIN,20));
        phoneNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
    }

    public void setColor(){
        title.setForeground(Color.ORANGE);
        //signup.setForeground(Color.ORANGE);
        signup.setBackground(Color.ORANGE);
        loginButton.setBackground(Color.ORANGE);
        resetButton.setBackground(Color.decode("0x121E31"));
        resetButton.setForeground(Color.ORANGE);
        phoneNumberLabel.setForeground(Color.white);
        passwordLabel.setForeground(Color.white);
        showPassword.setBackground(Color.decode("0x121E31"));
        showPassword.setForeground(Color.ORANGE);
        container.setBackground(Color.decode("0x121E31"));
    }

    public void addComponentsToContainer() {
        container.add(phoneNumberLabel);
        container.add(passwordLabel);
        container.add(phoneTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(title);
        container.add(signup);
    }

    public void addActionEvent(){
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        signup.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = phoneTextField.getText();
            pwdText = passwordField.getText();
        if(userText.matches("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$")) {
                /*if (userText.equalsIgnoreCase("9655569935") && pwdText.equalsIgnoreCase("12345")) {
                    JOptionPane.showMessageDialog(this, "Login Successful");
                } else {
                    JOptionPane.showMessageDialog(this, "Phone number and password dont match");
                }*/
          LoginDB l=  new LoginDB(userText,pwdText);
          if(l.checkLogin()) {
        	  HomeScreen h=new HomeScreen(phoneTextField.getText());
        	  this.dispose();
        	  
          }
          
            }
            else{
                JOptionPane.showMessageDialog(this, "Please enter a valid phone number");
            }
        }

        if (e.getSource() == resetButton) {
            phoneTextField.setText("");
            passwordField.setText("");
        }

        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }

        }
        if(e.getSource()==signup){
            new Registration();
            dispose();
        }
    }
    public static void main(String[] a){
        new LoginFrame();
    }
}