import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameForm2 extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField preferredNameField;
    private JTextField dobField;
    private JTextField hometownField;

    public GameForm2() {
        // Window properties
        setTitle("PassWorks!");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full-screen mode
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout()); // Use GridBagLayout for dynamic resizing

        // Gradient background
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                Color color1 = new Color(0, 0, 128); // Dark Blue
                Color color2 = Color.BLACK;
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        });

        // Title Label
        JLabel titleLabel = new JLabel("🎮 PassWorks! 🎮", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 72)); // Larger font size
        titleLabel.setForeground(Color.WHITE);

        // Add title label to the center of the screen
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(20, 20, 40, 20); // Add padding
        add(titleLabel, gbc);

        // Create and add text fields
        firstNameField = createTextField("First Name:", 1);
        lastNameField = createTextField("Last Name:", 2);
        preferredNameField = createTextField("Preferred Name:", 3);
        dobField = createTextField("Date of Birth:", 4);
        hometownField = createTextField("Hometown:", 5);

        // Next Button
        JButton nextButton = createButton("Next ➡", new Color(50, 205, 50), e -> nextPage());
        gbc.gridy = 6;
        gbc.gridwidth = 2; // Span across two columns
        add(nextButton, gbc);

        setVisible(true);
    }

    private JTextField createTextField(String labelText, int gridY) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 48)); // Larger font size
        label.setForeground(Color.WHITE);

        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.BOLD, 36)); // Larger font size
        textField.setBackground(new Color(173, 216, 230)); // Light Blue

        // Add label and text field to the layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding
        add(label, gbc);

        gbc.gridx = 1;
        add(textField, gbc);

        // Add Enter key listener to move to the next field
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (gridY < 5) {
                        switch (gridY) {
                            case 1 -> lastNameField.requestFocus();
                            case 2 -> preferredNameField.requestFocus();
                            case 3 -> dobField.requestFocus();
                            case 4 -> hometownField.requestFocus();
                        }
                    } else {
                        nextPage();
                    }
                }
            }
        });

        return textField;
    }

    private JButton createButton(String text, Color color, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 48)); // Larger font size
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.addActionListener(action);
        return button;
    }

    private void nextPage() {
        dispose();
        new PasswordSetupPage();
    }

    public class PasswordSetupPage extends JFrame {
        private JPasswordField passwordField, confirmPasswordField;
        private JLabel timerLabel, confirmPassLabel;
        private Timer timer;
        private int timeLeft = 10;
        private boolean confirmFieldVisible = false;

        public PasswordSetupPage() {
            setTitle("Set Your Funky Password!");
            setExtendedState(JFrame.MAXIMIZED_BOTH); // Full-screen mode
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridBagLayout()); // Use GridBagLayout for dynamic resizing

            // Gradient background
            setContentPane(new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    Color color1 = new Color(0, 0, 128); // Dark Blue
                    Color color2 = Color.BLACK;
                    GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                    g2d.setPaint(gp);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            });

            // Timer Label
            timerLabel = new JLabel("⏳ Time left: " + timeLeft + " seconds", SwingConstants.CENTER);
            timerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 72)); // Larger font size
            timerLabel.setForeground(Color.YELLOW);

            // Add timer label to the center of the screen
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2; // Span across two columns
            gbc.insets = new Insets(20, 20, 40, 20); // Add padding
            add(timerLabel, gbc);

            // Password Field
            JLabel passLabel = new JLabel("Create Password:");
            passLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 48)); // Larger font size
            passLabel.setForeground(Color.WHITE);

            passwordField = new JPasswordField(20);
            passwordField.setFont(new Font("Arial", Font.BOLD, 36)); // Larger font size
            passwordField.setBackground(Color.WHITE);

            gbc.gridy = 1;
            gbc.gridwidth = 2; // Span across two columns
            add(passLabel, gbc);

            gbc.gridy = 2;
            add(passwordField, gbc);

            // Confirm Password Field (Initially Hidden)
            confirmPassLabel = new JLabel("Confirm Password:");
            confirmPassLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 48)); // Larger font size
            confirmPassLabel.setForeground(Color.WHITE);
            confirmPassLabel.setVisible(false);

            confirmPasswordField = new JPasswordField(20);
            confirmPasswordField.setFont(new Font("Arial", Font.BOLD, 36)); // Larger font size
            confirmPasswordField.setBackground(Color.WHITE);
            confirmPasswordField.setVisible(false);

            gbc.gridy = 3;
            add(confirmPassLabel, gbc);

            gbc.gridy = 4;
            add(confirmPasswordField, gbc);

            // Submit Button
            JButton submitButton = createButton("Submit ✅", new Color(255, 0, 0), e -> submitPassword());
            gbc.gridy = 5;
            gbc.gridwidth = 2; // Span across two columns
            add(submitButton, gbc);

            // Timer logic
            timer = new Timer(1000, e -> {
                timeLeft--;
                timerLabel.setText("⏳ Time left: " + timeLeft + " seconds");

                if (timeLeft <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(PasswordSetupPage.this, "⏰ Time's up! Restarting...");
                    dispose();
                }
            });
            timer.start();

            // First Password Field Listener
            passwordField.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (!confirmFieldVisible) {
                            confirmFieldVisible = true;
                            confirmPassLabel.setVisible(true);
                            confirmPasswordField.setVisible(true);
                            confirmPasswordField.requestFocus();
                            timeLeft = 8; // Reset timer to 8 seconds
                            timerLabel.setText("⏳ Time left: " + timeLeft + " seconds");
                        }
                    }
                }
            });

            // Confirm Password Field Listener
            confirmPasswordField.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        submitPassword();
                    }
                }
            });

            setVisible(true);
        }

        private void submitPassword() {
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!password.isEmpty() && password.equals(confirmPassword)) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "Password Confirmed!");
                dispose();
                new QuestionPage(1); // Move to the first question page
            } else {
                JOptionPane.showMessageDialog(this, "⚠ Passwords do not match!");
            }
        }
    }

    public class QuestionPage extends JFrame {
        private int questionNumber;
        private JLabel questionLabel;
        private JTextField textField;
        private JButton yesButton, noButton;
        private String[] responses = new String[20]; // Array to store user responses

        // List of 20 questions
        private String[] questions = {
            "What is the length of your password?",
            "Does your password contain your name?",
            "Does your password contain your date of birth?",
            "Does your password contain your Hometown?",
            "How many upper letters in your password if any?",
            "How many special characters in your password?",
            "How many numbers in your password?",
            "Are there any repeating characters in your password?",
            "Does your password contain any famous landmark?",
            "Is your password a TV/Anime/Movie character?",
            "Does your password contain keyboard patterns (e.g., '123456', 'qwerty', 'asdfgh')?",
            "Is your password based on a fictional place (e.g., Hogwarts, Gotham)?",
            "Is your password a simple phrase (e.g., 'Iloveyou', 'letmein')?",
            "Is your password fewer than 12 characters long?",
            "Does your password contain a sports team name?",
            "Does your password contain your favorite celebrity's name?",
            "Does your password include a birth year (e.g., '1999', '2000')?",
            "Does your password contain your university name?",
            "Is your password in English?",
            "Are you Sane?"
        };

        public QuestionPage(int questionNumber) {
            this.questionNumber = questionNumber;
            setTitle("Question " + questionNumber);
            setExtendedState(JFrame.MAXIMIZED_BOTH); // Full-screen mode
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridBagLayout()); // Use GridBagLayout for dynamic resizing

            // Gradient background
            setContentPane(new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    Color color1 = new Color(0, 0, 128); // Dark Blue
                    Color color2 = Color.BLACK;
                    GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                    g2d.setPaint(gp);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            });

            // Question Label
            questionLabel = new JLabel(questions[questionNumber - 1], SwingConstants.CENTER);
            questionLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 48)); // Larger font size
            questionLabel.setForeground(Color.WHITE);

            // Add question label to the center of the screen
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2; // Span across two columns
            gbc.insets = new Insets(20, 20, 40, 20); // Add padding
            add(questionLabel, gbc);

            // Check if the question requires a text box
            if (requiresTextBox(questionNumber)) {
                // Add a text field for questions that need a text response
                textField = new JTextField(20);
                textField.setFont(new Font("Arial", Font.PLAIN, 36)); // Larger font size
                textField.setBackground(Color.WHITE);
                textField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            responses[questionNumber - 1] = textField.getText(); // Store the response
                            nextQuestion();
                        }
                    }
                });

                gbc.gridy = 1;
                gbc.gridwidth = 2; // Span across two columns
                add(textField, gbc);
            } else {
                // Add Yes/No buttons for yes/no questions
                yesButton = createButton("Yes", new Color(0, 255, 0), e -> {
                    responses[questionNumber - 1] = "Yes"; // Store the response
                    nextQuestion();
                });

                noButton = createButton("No", new Color(255, 0, 0), e -> {
                    responses[questionNumber - 1] = "No"; // Store the response
                    nextQuestion();
                });

                gbc.gridy = 1;
                gbc.gridx = 0;
                gbc.gridwidth = 1; // Reset gridwidth
                add(yesButton, gbc);

                gbc.gridx = 1;
                add(noButton, gbc);
            }

            setVisible(true);
        }

        // Helper method to check if a question requires a text box
        private boolean requiresTextBox(int questionNumber) {
            // Questions that require a text box
            return questionNumber == 1 || questionNumber == 5 || questionNumber == 6 || questionNumber == 7;
        }

        // Helper method to create a button
        private JButton createButton(String text, Color color, ActionListener action) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 48)); // Larger font size
            button.setForeground(Color.WHITE);
            button.setBackground(color);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            button.setOpaque(true);
            button.setContentAreaFilled(true);
            button.addActionListener(action);
            return button;
        }

        // Method to move to the next question
        private void nextQuestion() {
            dispose(); // Close the current question page
            if (questionNumber < 20) {
                new QuestionPage(questionNumber + 1); // Move to the next question
            } else {
                JOptionPane.showMessageDialog(this, "🎉 All questions answered!");
                // Display all responses (for testing purposes)
                StringBuilder allResponses = new StringBuilder();
                for (int i = 0; i < responses.length; i++) {
                    allResponses.append("Question ").append(i + 1).append(": ").append(responses[i]).append("\n");
                }
                JOptionPane.showMessageDialog(this, "Responses:\n" + allResponses.toString());
            }
        }
    }

    public static void main(String[] args) {
        new GameForm2();
    }
}