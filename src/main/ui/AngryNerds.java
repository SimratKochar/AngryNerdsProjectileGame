package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.Timer;

public class AngryNerds extends JFrame {
    private static final int DELAY = 10;

    private GamePanel gamePanel;
    private ScorePanel scorePanel;
    private StatsPanel statsPanel;

    // Constructs main window
    // EFFECTS: sets up window in which Angry Nerds will be played
    public AngryNerds() {
        super("Angry Nerds: A projectile motion game");
        initFrame();

        setVisible(true);
    }



    private void initFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        gamePanel = new GamePanel();
        scorePanel = new ScorePanel(gamePanel);
        statsPanel = new StatsPanel(gamePanel);

        this.add(gamePanel, BorderLayout.CENTER);
        this.add(scorePanel, BorderLayout.NORTH);
        this.add(statsPanel, BorderLayout.SOUTH);

    }
}
