package ui;

import service.DomainService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DomainPage {

    JFrame frame;

    public DomainPage(String user){

        frame = new JFrame("Select Domain");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel(){
            protected void paintComponent(Graphics g){

                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;

                GradientPaint gp = new GradientPaint(
                        0,0,new Color(210,230,255),
                        getWidth(),getHeight(),new Color(170,200,240)
                );

                g2.setPaint(gp);
                g2.fillRect(0,0,getWidth(),getHeight());

            }
        };

        panel.setLayout(null);

        JLabel title = new JLabel("SELECT QUIZ DOMAIN");

        title.setFont(new Font("Arial",Font.BOLD,36));

        title.setBounds(600,80,500,50);

        panel.add(title);

        frame.add(panel);

        frame.setVisible(true);

        loadDomains(panel,user);

    }

    void loadDomains(JPanel panel,String user){

        List<String> domains = DomainService.getDomains();

        int x = 350;
        int y = 200;

        int col = 0;

        for(String d : domains){

            String domainName = d.split(" - ")[1];
            int domainId = Integer.parseInt(d.split(" - ")[0]);

            JButton btn = new JButton(domainName);

            btn.setFont(new Font("Arial",Font.BOLD,16));

            btn.setBackground(new Color(120,180,255));

            btn.setFocusPainted(false);

            btn.setBounds(x,y,250,80);

            panel.add(btn);

            btn.addActionListener(e -> {

                frame.dispose();

                new QuizPage(domainId,user,domainName);

            });

            x += 300;

            col++;

            if(col == 3){

                col = 0;
                x = 350;
                y += 120;

            }

        }

    }

}