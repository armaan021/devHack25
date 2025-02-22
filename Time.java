import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Time extends JFrame {
    private JLabel timerLabel;
    private JTextField inputField;
    private JButton startButton;
    private Timer timer;
    private int timeRemaining;

    public Time() {
        // Set up the JFrame
        setTitle("Countdown Timer");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create components
        timerLabel = new JLabel("Enter time in seconds and click Start");
        inputField = new JTextField(10);
        startButton = new JButton("Start");

        // Add components to the JFrame
        add(timerLabel);
        add(inputField);
        add(startButton);

        // Add action listener to the start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });

        // Set the JFrame visible
        setVisible(true);
    }

    private void startTimer() {
        try {
            // Get the input time from the text field
            timeRemaining = Integer.parseInt(inputField.getText());

            // Disable the start button and input field while the timer is running
            startButton.setEnabled(false);
            inputField.setEnabled(false);

            // Create a Swing Timer to update the countdown every second
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (timeRemaining > 0) {
                        timerLabel.setText("Time remaining: " + timeRemaining + " seconds");
                        timeRemaining--;
                    } else {
                        // Stop the timer when it reaches 0
                        ((Timer) e.getSource()).stop();
                        timerLabel.setText("Time's up!");
                        startButton.setEnabled(true);
                        inputField.setEnabled(true);
                    }
                }
            });

            // Start the timer
            timer.start();
        } catch (NumberFormatException ex) {
            // Handle invalid input
            timerLabel.setText("Invalid input! Enter a number.");
        }
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Time();
            }
        });
    }
}