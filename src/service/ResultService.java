package service;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ResultService {

    public static void saveScore(String username,int domainId,int score){

        try{

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO leaderboard(username,domain_id,score) VALUES(?,?,?)"
            );

            ps.setString(1,username);
            ps.setInt(2,domainId);
            ps.setInt(3,score);

            ps.executeUpdate();

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}