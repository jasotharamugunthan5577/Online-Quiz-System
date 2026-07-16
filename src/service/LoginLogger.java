package service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoginLogger {

    public static void log(String username){

        try(FileWriter fw = new FileWriter("login_logs.txt",true)){

            fw.write(username + " logged in at "
                    + LocalDateTime.now() + "\n");

        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

}