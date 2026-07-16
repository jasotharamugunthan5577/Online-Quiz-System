package ui;

import database.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterPage {

    public RegisterPage(){

        JFrame frame = new JFrame("Register");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);

        JLabel user = new JLabel("Username");
        JLabel pass = new JLabel("Password");

        JTextField username = new JTextField();
        JTextField password = new JTextField();

        JButton register = new JButton("Register");

        user.setBounds(650,300,100,40);
        pass.setBounds(650,360,100,40);

        username.setBounds(760,300,250,40);
        password.setBounds(760,360,250,40);

        register.setBounds(750,450,150,50);

        frame.add(user);
        frame.add(pass);
        frame.add(username);
        frame.add(password);
        frame.add(register);

        frame.setVisible(true);

        register.addActionListener(e -> {

            try{

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO users(username,password,role) VALUES(?,?,?)"
                );

                ps.setString(1,username.getText());
                ps.setString(2,password.getText());
                ps.setString(3,"user");

                ps.executeUpdate();

                JOptionPane.showMessageDialog(frame,"User Registered");

                frame.dispose();

                new LoginPage();

            }
            catch(Exception ex){

                ex.printStackTrace();

            }

        });

    }

}