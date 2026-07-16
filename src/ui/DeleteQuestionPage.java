package ui;

import database.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteQuestionPage {

    public DeleteQuestionPage(){

        JFrame frame = new JFrame("Delete Question");

        JLabel label = new JLabel("Enter Question ID");

        JTextField idField = new JTextField();

        JButton delete = new JButton("Delete");

        label.setBounds(40,40,150,30);
        idField.setBounds(170,40,100,30);
        delete.setBounds(110,100,100,30);

        frame.add(label);
        frame.add(idField);
        frame.add(delete);

        frame.setSize(320,200);
        frame.setLayout(null);
        frame.setVisible(true);

        delete.addActionListener(e -> {

            try{

                int id = Integer.parseInt(idField.getText());

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM questions WHERE question_id=?"
                );

                ps.setInt(1,id);

                int rows = ps.executeUpdate();

                if(rows > 0){

                    JOptionPane.showMessageDialog(frame,"Question Deleted");

                }else{

                    JOptionPane.showMessageDialog(frame,"Question Not Found");

                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        });

    }
}