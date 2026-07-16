package ui;

import database.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ManageUsersPage {

    JFrame frame;

    JTable table;

    DefaultTableModel model;

    public ManageUsersPage(){

        frame = new JFrame("Manage Users");

        frame.setSize(500,400);

        frame.setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID","Username","Role"},0
        );

        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);

        frame.add(scroll,BorderLayout.CENTER);

        JPanel panel = new JPanel();

        JButton add = new JButton("Add User");
        JButton delete = new JButton("Delete User");
        JButton back = new JButton("Back");

        panel.add(add);
        panel.add(delete);
        panel.add(back);

        frame.add(panel,BorderLayout.SOUTH);

        frame.setVisible(true);

        loadUsers();

        // ADD USER

        add.addActionListener(e -> addUser());

        // DELETE USER

        delete.addActionListener(e -> deleteUser());

        // BACK

        back.addActionListener(e -> {

            frame.dispose();

            new AdminPage();

        });

    }

    void loadUsers(){

        model.setRowCount(0);

        try{

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            "SELECT username,role FROM users"
                    );

            int count = 1;

            while(rs.next()){

                model.addRow(new Object[]{

                        count++,
                        rs.getString("username"),
                        rs.getString("role")

                });

            }

        }
        catch(Exception e){

            e.printStackTrace();

        }

    }

    void addUser(){

        try{

            String username =
                    JOptionPane.showInputDialog("Enter Username");

            String password =
                    JOptionPane.showInputDialog("Enter Password");

            String role =
                    JOptionPane.showInputDialog("Enter Role (user/admin)");

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            "INSERT INTO users(username,password,role) VALUES(?,?,?)"
                    );

            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,role);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null,"User Added");

            loadUsers();

        }
        catch(Exception e){

            e.printStackTrace();

        }

    }

    void deleteUser(){

        int row = table.getSelectedRow();

        if(row == -1){

            JOptionPane.showMessageDialog(null,"Select User");

            return;

        }

        String username =
                model.getValueAt(row,1).toString();

        try{

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            "DELETE FROM users WHERE username=?"
                    );

            ps.setString(1,username);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null,"User Deleted");

            loadUsers();

        }
        catch(Exception e){

            e.printStackTrace();

        }

    }

}