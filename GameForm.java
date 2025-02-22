import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameForm extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField preferredNameField;
    private JTextField dobField;
    private JTextField hometownField;

    public GameForm() {
        // Window properties
        setTitle("PassWorks!");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use the custom GradientPanel as the content pane
        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);

        JLabel titleLabel;

        // Load and use Techno Space font
        try {
            Font technoSpaceFont = Font.createFont(Font.TRUETYPE_FONT, new File("TechnoSpace.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(technoSpaceFont);

            // Initialize titleLabel with the custom font
            titleLabel = new JLabel("🎮 PassWorks! 🎮", SwingConstants.CENTER);
            titleLabel.setFont(technoSpaceFont.deriveFont(Font.BOLD, 36));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Fallback to Arial if Techno Space fails to load
            titleLabel = new JLabel("🎮 PassWorks! 🎮", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        }

        // Set common properties for titleLabel
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 20, 500, 50);
        add(titleLabel);

        // Creating labels and fields with spacing
        firstNameField = createTextField("First Name:", 150, 100);
        lastNameField = createTextField("Last Name:", 150, 180);
        preferredNameField = createTextField("Preferred Name:", 150, 260);
        dobField = createTextField("Date of Birth:", 150, 340);
        hometownField = createTextField("Hometown:", 150, 420);

        JTextField[] fields = {firstNameField, lastNameField, preferredNameField, dobField, hometownField};

        // Handle Enter Key to go to next field
        for (int i = 0; i < fields.length; i++) {
            int nextIndex = i + 1;
            fields[i].addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (nextIndex < fields.length) {
                            fields[nextIndex].requestFocus();
                        } else {
                            nextPage();
                        }
                    }
                }
            });
        }

        // Next Button
        JButton nextButton = createButton("Next ➡", 400, 550, new Color(50, 205, 50), e -> nextPage());
        add(nextButton);

        setVisible(true);
    }

    private JTextField createTextField(String labelText, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Roboto Mono", Font.BOLD, 24));
        label.setForeground(Color.WHITE); // Change text color to white
        label.setBounds(x, y, 300, 40);
        add(label);

        JTextField textField = new JTextField();
        textField.setFont(new Font("Roboto Mono", Font.BOLD, 22));
        textField.setBounds(x + 250, y, 400, 50);
        textField.setBackground(new Color(173, 216, 230)); // Light Blue
        add(textField);
        return textField;
    }

    private JButton createButton(String text, int x, int y, Color color, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto Mono", Font.BOLD, 28));
        button.setForeground(Color.BLACK); // Change text color to black
        button.setBackground(color);
        button.setBounds(x, y, 250, 70);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3)); // Change border color to white
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
            setSize(1000, 700);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Use the custom GradientPanel as the content pane
            GradientPanel gradientPanel = new GradientPanel();
            gradientPanel.setLayout(null);
            setContentPane(gradientPanel);

            // Timer Label
            timerLabel = new JLabel("⏳ Time left: " + timeLeft + " seconds", SwingConstants.CENTER);
            timerLabel.setFont(new Font("Roboto Mono", Font.BOLD, 32));
            timerLabel.setForeground(Color.YELLOW); // Keep yellow for visibility
            timerLabel.setBounds(300, 50, 400, 50);
            add(timerLabel);

            // Password Field
            JLabel passLabel = new JLabel("Create Password:");
            passLabel.setFont(new Font("Roboto Mono", Font.BOLD, 26));
            passLabel.setForeground(Color.WHITE); // Change text color to white
            passLabel.setBounds(250, 180, 300, 50);
            add(passLabel);

            passwordField = new JPasswordField();
            passwordField.setFont(new Font("Roboto Mono", Font.BOLD, 24));
            passwordField.setBounds(250, 230, 500, 50);
            passwordField.setBackground(Color.WHITE);
            add(passwordField);

            // Confirm Password Field (Initially Hidden)
            confirmPassLabel = new JLabel("Confirm Password:");
            confirmPassLabel.setFont(new Font("Roboto Mono", Font.BOLD, 26));
            confirmPassLabel.setForeground(Color.WHITE); // Change text color to white
            confirmPassLabel.setBounds(250, 300, 300, 50);
            confirmPassLabel.setVisible(false);
            add(confirmPassLabel);

            confirmPasswordField = new JPasswordField();
            confirmPasswordField.setFont(new Font("Roboto Mono", Font.BOLD, 24));
            confirmPasswordField.setBounds(250, 350, 500, 50);
            confirmPasswordField.setBackground(Color.WHITE);
            confirmPasswordField.setVisible(false);
            add(confirmPasswordField);

            // Submit Button
            JButton submitButton = createButton("Submit ✅", 400, 500, new Color(255, 0, 0), e -> submitPassword());
            add(submitButton);

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
                JOptionPane.showMessageDialog(this, "🎉 Password Set Successfully!");
        
                // Close the current window
                dispose();
        
                // Open the QuestionPage
                SwingUtilities.invokeLater(() -> {
                    new QuestionPage(1); // Start with the first question
                });
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
        private static String[] responses = new String[20]; // Store responses for all questions
        private static String[] questions = {
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
            setSize(1000, 700); // Same size as GameForm
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridBagLayout()); // Use GridBagLayout for dynamic resizing
    
            // Gradient background with grainy effect
            setContentPane(new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    
                    // Gradient from black to blue to purple
                    Color color1 = new Color(0, 0, 0); // Black
                    Color color2 = new Color(0, 0, 128); // Dark Blue
                    Color color3 = new Color(128, 0, 128); // Purple
                    GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color3);
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
    
                    // Grainy effect
                    Random rand = new Random();
                    for (int i = 0; i < getWidth(); i += 2) {
                        for (int j = 0; j < getHeight(); j += 2) {
                            int alpha = rand.nextInt(50); // Random alpha for grainy effect
                            g2d.setColor(new Color(255, 255, 255, alpha));
                            g2d.fillRect(i, j, 2, 2);
                        }
                    }
                }
            });
    
            // GridBagConstraints for layout
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Add padding
            gbc.anchor = GridBagConstraints.CENTER; // Center components
    
            // Question Label
            questionLabel = new JLabel(questions[questionNumber - 1], SwingConstants.CENTER);
            questionLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 36)); // Larger font size
            questionLabel.setForeground(Color.WHITE);
    
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2; // Span across two columns
            add(questionLabel, gbc);
    
            // Check if the question requires a text box
            if (requiresTextBox(questionNumber)) {
                // Add a text field for questions that need a text response
                textField = new JTextField(20);
                textField.setFont(new Font("Arial", Font.PLAIN, 24)); // Larger font size
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
                yesButton = createButton("Yes", new Color(50, 205, 50), e -> { // Green
                    responses[questionNumber - 1] = "Yes"; // Store the response
                    nextQuestion();
                });
    
                noButton = createButton("No", new Color(255, 0, 0), e -> { // Red
                    responses[questionNumber - 1] = "No"; // Store the response
                    nextQuestion();
                });
    
                // Create a panel to hold the buttons
                JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // 1 row, 2 columns, 10px gap
                buttonPanel.setOpaque(false); // Make the panel transparent
                buttonPanel.add(yesButton);
                buttonPanel.add(noButton);
    
                gbc.gridy = 1;
                gbc.gridwidth = 2; // Span across two columns
                add(buttonPanel, gbc);
            }
    
            // Add filler panels to center the content vertically
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.weighty = 1; // Allow vertical expansion
            add(Box.createVerticalGlue(), gbc); // Filler to push content up
    
            gbc.gridy = 0;
            gbc.weighty = 1; // Allow vertical expansion
            add(Box.createVerticalGlue(), gbc); // Filler to push content down
    
            setVisible(true);
        }
    
        // Helper method to check if a question requires a text box
        private boolean requiresTextBox(int questionNumber) {
            // Questions that require a text box
            return questionNumber == 1 || questionNumber == 3 || questionNumber == 5 || questionNumber == 6 || questionNumber == 7;
        }
    
        // Helper method to create a button
        private JButton createButton(String text, Color color, ActionListener action) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font size
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
            if (questionNumber < questions.length) {
                new QuestionPage(questionNumber + 1); // Move to the next question
            } else {
                JOptionPane.showMessageDialog(this, "🎉 All questions answered!");
                // Display all responses (for testing purposes)
                StringBuilder allResponses = new StringBuilder();
                for (int i = 0; i < responses.length; i++) {
                    if (responses[i] != null) {
                        allResponses.append("Question ").append(i + 1).append(": ").append(responses[i]).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(this, "Responses:\n" + allResponses.toString());
            }
        }
        
    }
    public static void main(String[] args) {
        new GameForm();
    }
}