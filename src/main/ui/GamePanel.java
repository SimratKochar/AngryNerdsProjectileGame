package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.NumberFormatter;

import model.ANGame;
import model.Projectile;
import model.ProjectileList;
// import model.Target;

/*
 * The panel in which the game is rendered
 */

public class GamePanel extends JPanel {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private static final String REPLAY = "You missed the target! Try again...";
    // private Projectile projectile;
    // private Target target;
    // private ProjectileList projList;
    private ANGame game;
    // private float time;
    // private boolean isWallHit;
    // private boolean isTargetHit;
    private boolean showText;

    private JTextArea velocityText;
    private JTextArea angleText;
    private JFormattedTextField velocityField;
    private JFormattedTextField angleField;
    private JButton launchButton;
    private JButton removeLastProjectileButton;
    private JButton replayLastProjectileButton;
    private JLabel endLabel;

    // Constructs a new GamePanel
    // EFFECTS: Sets dimensions and background color of panel
    // Creates new ProjectileList, Projectile and Target
    // Initializes booleans to assert that the projectile has not collided
    // Initializes fields for user to enter initial velocity and angle
    public GamePanel(ANGame game) {
        setSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        // this.projList = new ProjectileList();
        // this.target = new Target();
        // this.projectile = new Projectile(0, 0);
        // isWallHit = false;
        // isTargetHit = false;

        this.game = game;

        addEndGamePanel();

        addParameterFieldsAndButton();

        setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: adds the fields for initial velocity and angle, as well as the
    // Launch button
    private void addParameterFieldsAndButton() {
        initVelocityField();

        initAngleField();

        addLaunchButton(game);
        addRemoveLastProjectileButton();
        addReplayLastProjectileButton();
    }

    // MODIFIES: this
    // EFFECTS: creates a text field to allow user to enter initial velocity for
    // projectile
    // on the GamePanel
    private void initVelocityField() {
        velocityText = new JTextArea("Enter initial velocity: ");
        velocityField = new JFormattedTextField(new NumberFormatter());
        velocityText.setEditable(false);
        velocityText.setBackground(Color.BLACK);
        velocityText.setForeground(Color.WHITE);
        velocityField.setBackground(Color.DARK_GRAY);
        velocityField.setForeground(Color.WHITE);
        this.add(velocityText);
        this.add(velocityField);
        velocityText.setBounds(10, 12, 130, 20);
        velocityField.setBounds(140, 10, 100, 20);
    }

    // MODIFIES: this
    // EFFECTS: creates a text field to allow user to enter initial angle for
    // projectile
    // on the GamePanel
    private void initAngleField() {
        angleText = new JTextArea("Enter initial angle: ");
        angleField = new JFormattedTextField(new NumberFormatter());
        angleText.setEditable(false);
        angleText.setBackground(Color.BLACK);
        angleText.setForeground(Color.WHITE);
        angleField.setBackground(Color.DARK_GRAY);
        angleField.setForeground(Color.WHITE);
        this.add(angleText);
        this.add(angleField);
        angleText.setBounds(270, 12, 115, 20);
        angleField.setBounds(385, 10, 100, 20);
    }

    // MODIFIES: this
    // EFFECTS: creates a new Launch button which parses input from the velocity and
    // angle fields
    // to instantiate a new projectile
    private void addLaunchButton(ANGame game) {
        launchButton = new JButton("Launch");
        launchButton.setFocusable(false);
        launchButton.setForeground(Color.BLACK);
        launchButton.setBounds(530, 10, 50, 20);
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showText = false;

                int projectileVelocity = Integer.parseInt(velocityField.getText());
                int projectileAngle = Integer.parseInt(angleField.getText());

                Projectile newP = new Projectile(projectileVelocity, projectileAngle);
                game.initProjectile(newP);
                launchProjectile();
                repaint();
            }
        });
        this.add(launchButton);
    }

    // MODIFIES: this
    // EFFECTS: Adds a Remove Last Projectile button to the ScorePanel
    private void addRemoveLastProjectileButton() {
        removeLastProjectileButton = new JButton("Remove Last Projectile");
        removeLastProjectileButton.setFocusable(false);
        removeLastProjectileButton.setBounds(660, 10, 150, 20);
        removeLastProjectileButton.addActionListener(e -> removeLastProjectile());
        this.add(removeLastProjectileButton);
    }

    // MODIFIES: gp
    // EFFECTS: Removes the last projectile in gp's ProjectileList
    private void removeLastProjectile() {
        ProjectileList projList = game.getProjectiles();
        List<Projectile> projectiles = projList.getProjectiles();
        if (!projectiles.isEmpty()) {
            showText = false;
            projectiles.removeLast();
            game.setProjectile(projectiles.getLast());
            enableLaunchButton();
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a Replay Last Projectile button to the ScorePanel
    private void addReplayLastProjectileButton() {
        replayLastProjectileButton = new JButton("Replay Last Projectile");
        replayLastProjectileButton.setFocusable(false);
        replayLastProjectileButton.setBounds(830, 10, 150, 20);
        replayLastProjectileButton.addActionListener(e -> replayLastProjectile());
        this.add(replayLastProjectileButton);
    }

    // MODIFIES: gp
    // EFFECTS: Launches the last Projectile in ANGame's ProjectileList again
    private void replayLastProjectile() {
        List<Projectile> projectiles = game.getProjectiles().getProjectiles();
        if (!projectiles.isEmpty()) {
            launchProjectile();
        }
    }

    // MODIFIES: this, p
    // EFFECTS: resets the game state and prepares for launch
    public void launchProjectile() {
        showText = false;
        endLabel.setVisible(false);
        game.launchProjectile();
        repaint();
    }

    // // Updates the panel and projetile's coordinates on clock tick
    // // MODIFIES: this, p
    // // EFFECTS: advances time, projectile's coordinates while checking if the
    // // projectile has hit the wall or target
    // public void update() {
    //     if (!isWallHit && !isTargetHit) {
    //         this.projectile.updateCoordinates(time);
    //         time += 0.1;
    //     }
    //     checkProjectileCollision();
    //     repaint();
    // }

    // // EFFECTS: updates the collision status of projectile based on its position
    // private void checkProjectileCollision() {
    //     if (!projList.getProjectiles().isEmpty()) {
    //         if (projectile.targetHit(this.target)) {
    //             isTargetHit = true;
    //             showText = true;
    //         } else if (projectile.groundHit() || (projectile.getX() > 1000)) {
    //             isWallHit = true;
    //             showText = true;
    //         }
    //     }
    // }

    // // REQUIRES: A valid ProjectileList
    // // MODIFIES: this
    // // EFFECTS: Updates the panel's ProjectileList to projList
    // public void setProjectiles(ProjectileList projList) {
    //     this.projList = projList;
    // }

    // // REQUIRES: A valid Projectile
    // // MODIFIES: this
    // // EFFECTS: Updates the panel's Projectile to p
    // public void setProjectile(Projectile p) {
    //     this.projectile = p;
    // }

    // // REQUIRES: A valid Target
    // // MODIFIES: this
    // // EFFECTS: Updates the panel's Target to t
    // public void setTarget(Target t) {
    //     this.target = t;
    // }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

        if (showText) {
            if (game.isTargetHit()) {
                gameOver(g);
            } else if (game.isWallHit()) {
                replayGame(g);
            }
        }
    }

    public void update() {
        if (game.checkProjectileCollision()) {
            this.showText = true;
        }
    }

    // Draws the game
    // MODIFIES: g
    // EFFECTS: Draws the game onto g
    public void drawGame(Graphics g) {
        game.draw(g);
    }

    // // Draws the projectile
    // // MODIFIES: g
    // // EFFECTS: Draws the projectile onto g
    // private void drawProjectile(Graphics g) {
    //     Color savedCol = g.getColor();
    //     g.setColor(Projectile.COLOR);
    //     g.fillOval((int) projectile.getX() - Projectile.SIZE / 2,
    //             (int) projectile.getY() - Projectile.SIZE / 2,
    //             Projectile.SIZE, Projectile.SIZE);
    //     g.setColor(savedCol);
    // }

    // // Draws the target
    // // MODIFIES: g
    // // EFFECTS: Draws the target onto g
    // private void drawTarget(Graphics g) {
    //     Color savedCol = g.getColor();
    //     g.setColor(Target.COLOR);
    //     g.fillOval(target.getX(), target.getY(), Target.SIZE, Target.SIZE);
    //     g.setColor(savedCol);
    // }

    // From the SpaceInvaders Lecture Repo
    // Draws the "replay" message
    // modifies: g
    // effects: draws "replay" message
    private void replayGame(Graphics g) {
        Color saved = g.getColor();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(REPLAY, g, fm, GamePanel.HEIGHT / 2);
        g.setColor(saved);

    }

    // Shows the panel indicating end of the game and disables further projectile
    // launches
    // modifies: g
    // effects: Sets the panel indicating end of the game to visible, and disables
    // the Launch button
    private void gameOver(Graphics g) {
        endLabel.setVisible(true);
        launchButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: Instantiates a new panel with image, which is initially not visible
    private void addEndGamePanel() {
        ImageIcon endIcon = new ImageIcon("data/GameEnd.jpeg");
        Image scaledEndIcon = endIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledEndIcon);
        endLabel = new JLabel(scaledIcon);
        this.add(endLabel);
        endLabel.setBounds(300, 90, 400, 400);
        endLabel.setVisible(false);
    }

    // From the SpaceInvaders Lecture Repo
    // Centres a string on the screen
    // modifies: g
    // effects: centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int posY) {
        int width = fm.stringWidth(str);
        g.drawString(str, (GamePanel.WIDTH - width) / 2, posY);
    }



    // // Sets the panel's current Projectile to p and adds it to the list of
    // // Projectiles
    // // MODIFIES: this, projList
    // // EFFECTS: Sets current Projectile to p and adds it to panel's ProjectileList
    // public void createProjectile(Projectile p) {
    //     setProjectile(p);
    //     this.projList.addProjectile(p);
    // }

    // Enables the launch button. ONLY USED WHEN THE PROJECTILE THAT HIT THE TARGET
    // IS REMOVED
    // MODIFIES: launchButton, endLabel
    // EFFECTS: Enables the launch button, changes collision status of projectile
    // and ensures that the endLabel is not visible
    public void enableLaunchButton() {
        game.setWallHit();
        endLabel.setVisible(false);
        launchButton.setEnabled(true);
    }

    // // EFFECTS: Returns the list of projectiles
    // public ProjectileList getProjectiles() {
    //     return this.projList;
    // }

    // // EFFECTS: Returns the current projectile
    // public Projectile getProjectile() {
    //     return this.projectile;
    // }

    // // EFFECTS: returns the current target
    // public Target getTarget() {
    //     return this.target;
    // }

    // // EFFECTS: returns collision status between projectile and target
    // public boolean isTargetHit() {
    //     return this.isTargetHit;
    // }
}
