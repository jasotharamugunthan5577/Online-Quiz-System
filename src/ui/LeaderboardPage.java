package ui;

import database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LeaderboardPage {

    JFrame frame;
    JTextArea area;

    public LeaderboardPage(){

        frame = new JFrame("Leaderboard");

        frame.setSize(500,400);

        area = new JTextArea();

        JScrollPane scroll = new JScrollPane(area);

        frame.add(scroll);

        frame.setVisible(true);

        loadLeaderboard();

    }

    void loadLeaderboard(){

        try{

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            "SELECT l.username, d.domain_name, l.score " +
                                    "FROM leaderboard l " +
                                    "JOIN domains d ON l.domain_id = d.domain_id " +
                                    "ORDER BY l.score DESC"
                    );

            area.setText("Rank | Username | Domain | Score\n\n");

            int rank = 1;

            while(rs.next()){

                area.append(
                        rank++ + " | "
                                + rs.getString("username") + " | "
                                + rs.getString("domain_name") + " | "
                                + rs.getInt("score") + "\n"
                );

            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}