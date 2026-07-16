package model;

import java.util.*;

public class Question {

    public String question;
    public String op1;
    public String op2;
    public String op3;
    public String op4;

    public int answer;

    public Question(String q,String a,String b,String c,String d,int ans){

        question = q;

        List<String> options = new ArrayList<>();

        options.add(a);
        options.add(b);
        options.add(c);
        options.add(d);

        String correctOption = options.get(ans-1);

        Collections.shuffle(options);

        op1 = options.get(0);
        op2 = options.get(1);
        op3 = options.get(2);
        op4 = options.get(3);

        if(op1.equals(correctOption)) answer = 1;
        else if(op2.equals(correctOption)) answer = 2;
        else if(op3.equals(correctOption)) answer = 3;
        else answer = 4;
    }

}