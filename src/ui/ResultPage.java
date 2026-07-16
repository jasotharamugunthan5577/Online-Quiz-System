package ui;

import model.Question;
import service.DomainClusterRecommendation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResultPage {

    JFrame frame;

    public ResultPage(int score, List<Question> questions,
                      List<Integer> userAnswers, String domainName){

        frame = new JFrame("Quiz Result");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel main = new JPanel(new BorderLayout());

        main.setBackground(new Color(240,248,255));

        // SCORE PANEL

        JLabel scoreLabel = new JLabel(
                "🎯 Final Score : " + score + " / " + questions.size()
        );

        scoreLabel.setFont(new Font("Segoe UI",Font.BOLD,34));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        scoreLabel.setBorder(BorderFactory.createEmptyBorder(30,0,20,0));

        main.add(scoreLabel,BorderLayout.NORTH);

        // REVIEW PANEL

        JTextArea review = new JTextArea();

        review.setFont(new Font("Segoe UI",Font.PLAIN,17));
        review.setEditable(false);
        review.setBackground(new Color(250,250,255));

        JScrollPane scroll = new JScrollPane(review);

        scroll.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(180,200,255),2),
                        "📊 Quiz Review",
                        0,
                        0,
                        new Font("Segoe UI",Font.BOLD,20)
                )
        );

        review.append("\n");

        for(int i=0;i<questions.size();i++){

            Question q = questions.get(i);

            int userAns = 0;

            if(i < userAnswers.size()){
                userAns = userAnswers.get(i);
            }

            review.append((i+1)+") "+q.question+"\n");

            review.append("Your Answer : "+optionText(q,userAns)+"\n");

            if(userAns == q.answer){

                review.append("✔ Yes, the answer is correct\n");

            }
            else{

                review.append("❌ No, the answer is incorrect\n");

            }

            review.append("Correct Answer : "+optionText(q,q.answer)+"\n");

            review.append(
                    "\n--------------------------------------------------\n\n"
            );

        }

        main.add(scroll,BorderLayout.CENTER);

        // RECOMMENDATION PANEL

        JPanel recPanel = new JPanel();

        recPanel.setLayout(new BoxLayout(recPanel,BoxLayout.Y_AXIS));

        recPanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(150,200,255),2),
                        "⭐ Recommended Courses",
                        0,
                        0,
                        new Font("Segoe UI",Font.BOLD,20)
                )
        );

        recPanel.setBackground(new Color(230,240,255));

        List<String> rec =
                DomainClusterRecommendation.recommendDomains(domainName);

        for(String d : rec){

            JLabel l = new JLabel("🧠 " + d);

            l.setFont(new Font("Segoe UI",Font.BOLD,18));

            l.setBorder(BorderFactory.createEmptyBorder(10,20,10,10));

            recPanel.add(l);

        }

        main.add(recPanel,BorderLayout.SOUTH);

        frame.add(main);

        frame.setVisible(true);

    }

    String optionText(Question q,int option){

        switch(option){

            case 1: return q.op1;
            case 2: return q.op2;
            case 3: return q.op3;
            case 4: return q.op4;

            case 0: return "No Answer";

        }

        return "No Answer";

    }

}