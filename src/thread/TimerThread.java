package thread;

import javax.swing.*;

public class TimerThread extends Thread {

    int time;
    JLabel label;
    boolean running = true;
    Runnable onFinish;

    public TimerThread(int time, JLabel label, Runnable onFinish) {

        this.time = time;
        this.label = label;
        this.onFinish = onFinish;

    }

    public void stopTimer() {

        running = false;

    }

    public void run() {

        try {

            while (time >= 0 && running) {

                label.setText("Time : " + time);

                Thread.sleep(1000);

                time--;

            }

            if (running) {

                onFinish.run();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}