package service;

import database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DomainService {

    public static List<String> getDomains(){

        List<String> domains = new ArrayList<>();

        try{

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT domain_id,domain_name FROM domains"
            );

            while(rs.next()){

                String domain =
                        rs.getInt("domain_id")
                                + " - "
                                + rs.getString("domain_name");

                domains.add(domain);

            }

        }
        catch(Exception e){

            e.printStackTrace();

        }

        return domains;

    }

}