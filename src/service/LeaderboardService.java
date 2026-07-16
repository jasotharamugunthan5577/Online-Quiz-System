package service;

import database.DBConnection;
import java.sql.*;

public class LeaderboardService {

    public static void updateScore(String username,int domainId,int score){

        try(Connection con = DBConnection.getConnection()){

            PreparedStatement check = con.prepareStatement(
                    "SELECT score FROM leaderboard WHERE username=? AND domain_id=?"
            );

            check.setString(1,username);
            check.setInt(2,domainId);

            ResultSet rs = check.executeQuery();

            if(rs.next()){

                int oldScore = rs.getInt("score");

                if(score > oldScore){

                    PreparedStatement update = con.prepareStatement(
                            "UPDATE leaderboard SET score=? WHERE username=? AND domain_id=?"
                    );

                    update.setInt(1,score);
                    update.setString(2,username);
                    update.setInt(3,domainId);

                    update.executeUpdate();

                }

            } else {

                PreparedStatement insert = con.prepareStatement(
                        "INSERT INTO leaderboard(username,domain_id,score) VALUES(?,?,?)"
                );

                insert.setString(1,username);
                insert.setInt(2,domainId);
                insert.setInt(3,score);

                insert.executeUpdate();

            }

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}