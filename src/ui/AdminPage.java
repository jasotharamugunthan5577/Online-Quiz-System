package ui;

import javax.swing.*;

public class AdminPage {

    JFrame frame;

    public AdminPage(){

        frame = new JFrame("Admin Dashboard");

        JLabel title = new JLabel("ADMIN PANEL");


        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.setVisible(true);

        JButton addQuestion = new JButton("Add Question");
        JButton deleteQuestion = new JButton("Delete Question");
        JButton manageUsers = new JButton("Manage Users");
        JButton setTimer = new JButton("Set Timer");
        JButton leaderboard = new JButton("View Leaderboard");
        JButton logout = new JButton("Logout");

        title.setBounds(130,20,200,30);

        addQuestion.setBounds(80,70,180,30);
        deleteQuestion.setBounds(80,110,180,30);
        manageUsers.setBounds(80,150,180,30);
        setTimer.setBounds(80,190,180,30);
        leaderboard.setBounds(80,230,180,30);
        logout.setBounds(80,270,180,30);

        frame.add(title);
        frame.add(addQuestion);
        frame.add(deleteQuestion);
        frame.add(manageUsers);
        frame.add(setTimer);
        frame.add(leaderboard);
        frame.add(logout);

        frame.setSize(350,370);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // ADD QUESTION
        addQuestion.addActionListener(e -> {

            new AddQuestionPage();

        });

        // DELETE QUESTION
        deleteQuestion.addActionListener(e -> {

            new DeleteQuestionPage();

        });

        // MANAGE USERS
        manageUsers.addActionListener(e -> {

            new ManageUsersPage();

        });

        // TIMER SETTINGS
        setTimer.addActionListener(e -> {

            new TimerSettingsPage();

        });

        // LEADERBOARD
        leaderboard.addActionListener(e -> {

            new LeaderboardPage();

        });

        // LOGOUT
        logout.addActionListener(e -> {

            frame.dispose();

            new LoginPage();

        });

    }
}