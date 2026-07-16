package ui;

import database.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddQuestionPage {

    public AddQuestionPage(){

        JFrame frame = new JFrame("Add Question");

        JTextField question = new JTextField();
        JTextField op1 = new JTextField();
        JTextField op2 = new JTextField();
        JTextField op3 = new JTextField();
        JTextField op4 = new JTextField();
        JTextField answer = new JTextField();
        JTextField domain = new JTextField();

        JButton save = new JButton("Save");

        question.setBounds(20,20,350,30);
        op1.setBounds(20,60,350,30);
        op2.setBounds(20,100,350,30);
        op3.setBounds(20,140,350,30);
        op4.setBounds(20,180,350,30);
        answer.setBounds(20,220,350,30);
        domain.setBounds(20,260,350,30);
        save.setBounds(140,300,100,30);

        frame.add(question);
        frame.add(op1);
        frame.add(op2);
        frame.add(op3);
        frame.add(op4);
        frame.add(answer);
        frame.add(domain);
        frame.add(save);

        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);

        save.addActionListener(e -> {

            try{

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO questions(domain_id,question,option1,option2,option3,option4,correct_answer) VALUES(?,?,?,?,?,?,?)"
                );

                ps.setInt(1,Integer.parseInt(domain.getText()));
                ps.setString(2,question.getText());
                ps.setString(3,op1.getText());
                ps.setString(4,op2.getText());
                ps.setString(5,op3.getText());
                ps.setString(6,op4.getText());
                ps.setInt(7,Integer.parseInt(answer.getText()));

                ps.executeUpdate();

                JOptionPane.showMessageDialog(frame,"Question Added");

            }catch(Exception ex){
                ex.printStackTrace();
            }

        });
    }
}