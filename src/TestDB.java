import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {

    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quizdb",
                    "root",
                    "jasosuba@123");

            System.out.println("Database Connected Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
