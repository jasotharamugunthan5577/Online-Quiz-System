package service;

import database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TimerService {

    public static int getQuizTimer() {

        int timer = 10;   // default

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT quiz_timer FROM settings WHERE id=1");

            if (rs.next()) {

                timer = rs.getInt("quiz_timer");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return timer;
    }

}