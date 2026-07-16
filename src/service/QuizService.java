package service;

import database.DBConnection;
import model.Question;

import java.sql.*;
import java.util.*;

public class QuizService {

    public static List<Question> getQuestions(int domainId){

        List<Question> list = new ArrayList<>();

        try{

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM questions WHERE domain_id=?"
            );

            ps.setInt(1,domainId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                list.add(
                        new Question(
                                rs.getString("question"),
                                rs.getString("option1"),
                                rs.getString("option2"),
                                rs.getString("option3"),
                                rs.getString("option4"),
                                rs.getInt("correct_answer")
                        )
                );

            }

            // shuffle questions
            Collections.shuffle(list);

        }
        catch(Exception e){
            e.printStackTrace();
        }

        // return 10 random questions
        return list.subList(0,10);
    }

}