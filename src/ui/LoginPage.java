package ui;

import database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import service.LoginLogger;

public class LoginPage {

    JFrame frame;

    JTextField username;
    JPasswordField password;

    JCheckBox showPassword;

    JButton login;
    JButton register;

    public LoginPage(){

        frame = new JFrame("Online Quiz System");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setLayout(null);

        JLabel title = new JLabel("ONLINE QUIZ SYSTEM");
        title.setFont(new Font("Arial",Font.BOLD,32));

        JLabel userLabel = new JLabel("Username");
        JLabel passLabel = new JLabel("Password");

        username = new JTextField();
        password = new JPasswordField();

        showPassword = new JCheckBox("Show Password");

        login = new JButton("Login");
        register = new JButton("Register");

        title.setBounds(550,120,500,50);

        userLabel.setBounds(520,250,120,30);
        passLabel.setBounds(520,320,120,30);

        username.setBounds(650,250,350,35);
        password.setBounds(650,320,350,35);

        showPassword.setBounds(650,360,200,30);

        login.setBounds(650,420,150,45);
        register.setBounds(850,420,150,45);

        frame.add(title);

        frame.add(userLabel);
        frame.add(passLabel);

        frame.add(username);
        frame.add(password);

        frame.add(showPassword);

        frame.add(login);
        frame.add(register);

        frame.setVisible(true);

        // SHOW / HIDE PASSWORD

        showPassword.addActionListener(e -> {

            if(showPassword.isSelected()){

                password.setEchoChar((char)0);

            }
            else{

                password.setEchoChar('*');

            }

        });

        // LOGIN BUTTON

        login.addActionListener(e -> loginUser());

        // REGISTER PAGE

        register.addActionListener(e -> {

            frame.dispose();

            new RegisterPage();

        });

    }

    void loginUser(){

        String user = username.getText();

        String pass = new String(password.getPassword());

        try{

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND password=?"
            );

            ps.setString(1,user);
            ps.setString(2,pass);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                // ✅ LOG LOGIN
                LoginLogger.log(user);

                String role = rs.getString("role");

                frame.dispose();

                if(role.equals("admin")){
                    new AdminPage();
                }
                else{
                    new DomainPage(user);
                }

            }
            else{

                JOptionPane.showMessageDialog(null,"Invalid Login");

            }

        }
        catch(Exception e){

            e.printStackTrace();

        }

    }

}