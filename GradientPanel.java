import javax.swing.*;
import java.awt.*;
import java.util.Random;

class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Define the three colors and their positions (0.0 to 1.0)
        Color[] colors = {new Color(0, 0, 0), new Color(0, 0, 128), new Color(128, 0, 128)}; // Black, Blue, Purple
        float[] fractions = {0.0f, 0.5f, 1.0f}; // Positions for the colors

        // Create a LinearGradientPaint with three colors
        LinearGradientPaint gradient = new LinearGradientPaint(
                0, 0, width, height, fractions, colors
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);

        // Add grainy effect
        Random rand = new Random();
        for (int i = 0; i < width; i += 2) {
            for (int j = 0; j < height; j += 2) {
                int alpha = rand.nextInt(50); // Random alpha for grainy effect
                g2d.setColor(new Color(255, 255, 255, alpha));
                g2d.fillRect(i, j, 2, 2);
            }
        }
    }
}