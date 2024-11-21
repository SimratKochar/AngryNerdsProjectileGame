package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class AngryNerds extends JFrame {
    private static final int INTERVAL = 16;

    private GamePanel gamePanel;
    private ScorePanel scorePanel;
    private StatsPanel statsPanel;

    // Constructs main window
    // EFFECTS: sets up window in which Angry Nerds will be played
    public AngryNerds() {
        super("Angry Nerds: A projectile motion game");
        initFrame();

        setVisible(true);
        addTimer();
    }

    // Set up timer
    // MODIFIES: none
    // EFFECTS: initializes a timer that updates game each
    // INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gamePanel.revalidate();
                gamePanel.update();
                scorePanel.revalidate();
                scorePanel.update(gamePanel);
                statsPanel.update(gamePanel);
            }
        });
        t.start();
    }

    // MODIFIES: this
    // EFFECTS: Sets up the frame, initializes the panels
    public void initFrame() {
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
