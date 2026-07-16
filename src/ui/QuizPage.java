package ui;

import model.Question;
import service.QuizService;
import service.DomainClusterRecommendation;
import service.TimerService;
import thread.TimerThread;
import service.ResultService;
import service.LeaderboardService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuizPage {
    String username;
    int domainId;

    int index = 0;
    int score = 0;

    String domainName;

    List<Question> questions;
    List<Integer> userAnswers = new ArrayList<>();

    JFrame frame;

    JLabel questionLabel;
    JLabel timerLabel;

    JRadioButton op1, op2, op3, op4;

    ButtonGroup group;

    JButton next;

    TimerThread timerThread;

    public QuizPage(int domainId, String username, String domainName) {

        this.domainId = domainId;
        this.username = username;
        this.domainName = domainName;

        questions = QuizService.getQuestions(domainId);

        frame = new JFrame("Quiz");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 22));

        timerLabel = new JLabel();

        op1 = new JRadioButton();
        op2 = new JRadioButton();
        op3 = new JRadioButton();
        op4 = new JRadioButton();

        group = new ButtonGroup();

        group.add(op1);
        group.add(op2);
        group.add(op3);
        group.add(op4);

        next = new JButton("Next");

        questionLabel.setBounds(200,120,900,40);
        timerLabel.setBounds(1100,120,200,40);

        op1.setBounds(200,220,600,40);
        op2.setBounds(200,260,600,40);
        op3.setBounds(200,300,600,40);
        op4.setBounds(200,340,600,40);

        next.setBounds(200,420,120,40);

        frame.add(questionLabel);
        frame.add(timerLabel);

        frame.add(op1);
        frame.add(op2);
        frame.add(op3);
        frame.add(op4);

        frame.add(next);

        frame.setVisible(true);

        showQuestion();

        startTimer();

        next.addActionListener(e -> {

            timerThread.stopTimer();

            checkAnswer();

            index++;

            if (index < questions.size()) {

                showQuestion();

                startTimer();

            } else {

                showResult();

            }

        });

    }

    void showQuestion() {

        Question q = questions.get(index);

        questionLabel.setText((index + 1) + ". " + q.question);

        op1.setText(q.op1);
        op2.setText(q.op2);
        op3.setText(q.op3);
        op4.setText(q.op4);

        group.clearSelection();

    }

    void checkAnswer() {

        if (index >= questions.size()) return;

        Question q = questions.get(index);

        int selected = 0;

        if (op1.isSelected()) selected = 1;
        if (op2.isSelected()) selected = 2;
        if (op3.isSelected()) selected = 3;
        if (op4.isSelected()) selected = 4;

        userAnswers.add(selected);

        if (selected == q.answer) {

            score++;

        }

    }

    void startTimer() {

        int timerValue = TimerService.getQuizTimer();

        timerThread = new TimerThread(timerValue, timerLabel, () -> {

            checkAnswer();

            index++;

            if (index < questions.size()) {

                showQuestion();

                startTimer();

            } else {

                showResult();

            }

        });

        timerThread.start();

    }

    void showResult() {
        // ✅ SAVE SCORE
        ResultService.saveScore(username, domainId, score);

// ✅ UPDATE LEADERBOARD (IMPORTANT)
        LeaderboardService.updateScore(username, domainId, score);

        frame.getContentPane().removeAll();

        frame.setLayout(new BorderLayout());

        JLabel scoreLabel = new JLabel(
                "Final Score : " + score + " / " + questions.size()
        );

        scoreLabel.setFont(new Font("Arial", Font.BOLD, 28));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea area = new JTextArea();
        area.setFont(new Font("Arial", Font.PLAIN, 16));
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area);

        area.append("========= QUIZ REVIEW =========\n\n");

        for (int i = 0; i < questions.size(); i++) {

            Question q = questions.get(i);

            int userAns = 0;

            if (i < userAnswers.size()) {
                userAns = userAnswers.get(i);
            }

            area.append((i + 1) + ") " + q.question + "\n");

            area.append("Your Answer : " + optionText(q, userAns) + "\n");

            if (userAns == q.answer) {

                area.append("Yes, the answer is correct\n");

            } else {

                area.append("No, the answer is incorrect\n");

            }

            area.append("Correct Answer : " + optionText(q, q.answer) + "\n");

            area.append("\n---------------------------------------\n\n");

        }

        List<String> rec =
                DomainClusterRecommendation.recommendDomains(domainName);

        area.append("\nRecommended Courses:\n\n");

        for (String d : rec) {

            area.append("• " + d + "\n");

        }

        frame.add(scoreLabel, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();

    }

    String optionText(Question q, int option) {

        switch (option) {

            case 1: return q.op1;
            case 2: return q.op2;
            case 3: return q.op3;
            case 4: return q.op4;
            case 0: return "No Answer";

        }

        return "No Answer";

    }

}