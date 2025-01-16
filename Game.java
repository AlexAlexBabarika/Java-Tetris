import java.awt.BorderLayout;

import javax.swing.*;

public class Game {
    public static Game instance;

    private JFrame frame;
    private FieldPanel gamePanel;
    private Field field;

    private int score = 0;
    private int level = 0;
    private Difficulty difficulty;

    private final int[] scoreToLevelUp = {200, 500, 1000, 2000, 5000, 7000, 10000};
    private final int[] levelDelay = {1000, 900, 800, 700, 600, 500, 400};

    public Game() {
        frame = new JFrame("Score: 0. Level: 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBackground(java.awt.Color.BLACK);

        field = new Field(10, 20);
        gamePanel = new FieldPanel(field);

        NextFigurePanelWithLabel nextFigurePanel = new NextFigurePanelWithLabel();

        frame.add(gamePanel);
        frame.add(nextFigurePanel, BorderLayout.EAST);

        frame.pack(); // Adjust the window size based on the panel
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);

        field.addRandomShape();

        instance = this;
    }

    public void updateScore(int score) {
        this.score += score;

        if (scoreToLevelUp[level] <= this.score && level < scoreToLevelUp.length) {
            level++;
            gamePanel.setNewDelay((int)(levelDelay[level] * difficulty.getSpeedMultiplier()));
        }

        frame.setTitle("Score: " + this.score + ". Level: " + (level + 1));
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        gamePanel.setNewDelay((int)(levelDelay[0] * difficulty.getSpeedMultiplier()));
    }
}

