package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import model.ANGame;
import model.Event;
import model.EventLog;

public class AngryNerds extends JFrame {
    private static final int INTERVAL = 16;

    private ANGame game;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;
    private StatsPanel statsPanel;

    // Constructs main window
    // EFFECTS: sets up window in which Angry Nerds will be played
    public AngryNerds() {
        super("Angry Nerds: A projectile motion game");
        this.setSize(1000, 800);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog eventLog = EventLog.getInstance();
                System.out.println("Actions completed:");
                for (Event event: eventLog) {
                    System.out.println(event.getDescription());
                }
                dispose();
            }
        });
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
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
                game.update();
                gamePanel.update();
                gamePanel.repaint();
                scorePanel.update();
                statsPanel.revalidate();
                statsPanel.update();
            }
        });
        t.start();
    }

    // MODIFIES: this
    // EFFECTS: Sets up the frame, initializes the panels
    public void initFrame() {
        game = new ANGame();
        gamePanel = new GamePanel(game);
        scorePanel = new ScorePanel(game);
        statsPanel = new StatsPanel(game);

        this.add(gamePanel, BorderLayout.CENTER);
        this.add(scorePanel, BorderLayout.NORTH);
        this.add(statsPanel, BorderLayout.SOUTH);
    }

}
