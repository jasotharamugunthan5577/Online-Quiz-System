package ui;

import database.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TimerSettingsPage {

    public TimerSettingsPage(){

        JFrame frame = new JFrame("Set Quiz Timer");

        JLabel label = new JLabel("Enter Time (seconds)");

        JTextField timeField = new JTextField();

        JButton save = new JButton("Save");

        label.setBounds(30,40,150,30);
        timeField.setBounds(180,40,80,30);
        save.setBounds(100,100,100,30);

        frame.add(label);
        frame.add(timeField);
        frame.add(save);

        frame.setSize(320,200);
        frame.setLayout(null);
        frame.setVisible(true);

        save.addActionListener(e -> {

            try{

                int time = Integer.parseInt(timeField.getText());

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE settings SET quiz_timer=? WHERE id=1"
                );

                ps.setInt(1,time);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(frame,"Timer Updated");

            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });

    }
}