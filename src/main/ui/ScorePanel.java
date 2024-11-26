package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
// import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ANGame;
import model.Projectile;
import model.ProjectileList;
import model.Target;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * Represents the Score panel which displays the current Attempt number,
 * previously entered velocities and previously entered angles in the same order
 * that they were input in.
 */
public class ScorePanel extends JPanel {

    private static final int WIDTH = 1000;
    public static final int HEIGHT = 100;
    private static final Dimension LBLDIMENSION = new Dimension(300, 50);
    private static final String VELOCITIESTXT = "Previously entered velocities: ";
    private static final String ANGLESTXT = "Previously entered angles: ";
    private static final String ATTEMPTNUMTXT = "Attempts till now: ";
    // private ProjectileList projectiles;
    private ANGame game;
    private static final String JSON_STORE = "./data/gameProgress.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JLabel velocitiesLbl;
    private JLabel anglesLbl;
    private JLabel numAttemptsLbl;
    private JButton saveButton;
    private JButton loadButton;
    private JButton newGameButton;

    // Constructs a new Score Panel
    // EFFECTS: Sets up a the background color and size and draws the labels and
    // buttons;
    // updates these with the GamePanel whose score is to be displayed.
    public ScorePanel(ANGame game) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GRAY);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        this.game = game;

        addLabels();

        addNewGameButton();
        addSaveButton();
        addLoadButton();
        // addRemoveLastProjectileButton(gp);
        // addReplayLastProjectileButton(gp);

        setLayout(null);
    }

    // Instantiates the labels on the scorePanel
    // MODIFIES: this
    // EFFECTS: Adds and the labels for Attempt Number, Velocities and Angles
    // previously entered
    private void addLabels() {
        ProjectileList projectiles = game.getProjectiles();
        velocitiesLbl = new JLabel(VELOCITIESTXT + projectiles.listVelocities());
        velocitiesLbl.setPreferredSize(LBLDIMENSION);
        anglesLbl = new JLabel(ANGLESTXT + projectiles.listAngles());
        anglesLbl.setPreferredSize(LBLDIMENSION);
        numAttemptsLbl = new JLabel(ATTEMPTNUMTXT + (projectiles.getNumAttempts()));
        numAttemptsLbl.setPreferredSize(new Dimension(250, 50));

        add(numAttemptsLbl);
        add(velocitiesLbl);
        add(anglesLbl);
        velocitiesLbl.setBounds(250, 5, 600, 50);
        anglesLbl.setBounds(250, 50, 600, 50);
        numAttemptsLbl.setBounds(50, 25, 250, 50);
    }

    // MODIFIES: this
    // EFFECTS: Updates the score panel with latest velocities, angles and attempt
    // number
    public void update() {
        remove(velocitiesLbl);
        remove(anglesLbl);
        remove(numAttemptsLbl);
        addLabels();

        repaint();
    }

    // MODIFIES: this
    // EFFECTS: Adds a New Game button to the ScorePanel
    private void addNewGameButton() {
        newGameButton = new JButton("New Game");
        newGameButton.setFocusable(false);
        newGameButton.setOpaque(true);
        newGameButton.setBounds(900, 10, 80, 20);
        newGameButton.addActionListener(e -> new AngryNerds());
        this.add(newGameButton);
    }

    // MODIFIES: this
    // EFFECTS: Adds a Save button to the ScorePanel
    private void addSaveButton() {
        saveButton = new JButton("Save Game");
        saveButton.setFocusable(false);
        saveButton.setSize(80, 20);
        saveButton.setBounds(900, 40, 80, 20);
        saveButton.addActionListener(e -> saveProgress());
        this.add(saveButton);
    }

    // MODIFIES: JSON_STORE file
    // EFFECTS: Saves the current state of the game to specified file JSON_STORE
    private void saveProgress() {
        try {
            jsonWriter.open();
            Target target = game.getTarget();
            ProjectileList projList = game.getProjectiles();
            jsonWriter.write(target, projList);
            jsonWriter.close();
            System.out.println("Saved progress to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a Load Game button to the ScorePanel
    private void addLoadButton() {
        loadButton = new JButton("Load Game");
        loadButton.setFocusable(false);
        loadButton.setBounds(900, 70, 80, 20);
        loadButton.addActionListener(e -> loadSavedGame());
        this.add(loadButton);
    }

    // MODIFIES: gp
    // EFFECTS: Reads game state from save file and updates the game panel accordingly
    private void loadSavedGame() {
        try {
            Target t = jsonReader.readTarget();
            ProjectileList projList = jsonReader.readPList();
            game.setTarget(t);
            game.setProjectiles(projList);
            Projectile lastProjectile = projList.getProjectiles().getLast();
            game.setProjectile(lastProjectile);
            // gp.launchProjectile();
            System.out.println("Loaded game from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE + ".");
        }
    }

    // // MODIFIES: this
    // // EFFECTS: Adds a Remove Last Projectile button to the ScorePanel
    // private void addRemoveLastProjectileButton(GamePanel gp) {
    //     removeLastProjectileButton = new JButton("Remove Last Projectile");
    //     removeLastProjectileButton.setFocusable(false);
    //     removeLastProjectileButton.setBounds(740, 25, 150, 20);
    //     removeLastProjectileButton.addActionListener(e -> removeLastProjectile(gp));
    //     this.add(removeLastProjectileButton);
    // }

    // // MODIFIES: gp
    // // EFFECTS: Removes the last projectile in gp's ProjectileList
    // private void removeLastProjectile(GamePanel gp) {
    //     ProjectileList projList = gp.getProjectiles();
    //     List<Projectile> projectiles = projList.getProjectiles();
    //     if (!projectiles.isEmpty()) {
    //         projectiles.removeLast();
    //         gp.setProjectile(projectiles.getLast());
    //         gp.enableLaunchButton();
    //     }
    // }

    // // MODIFIES: this
    // // EFFECTS: Adds a Replay Last Projectile button to the ScorePanel
    // private void addReplayLastProjectileButton(GamePanel gp) {
    //     replayLastProjectileButton = new JButton("Replay Last Projectile");
    //     replayLastProjectileButton.setFocusable(false);
    //     replayLastProjectileButton.setBounds(740, 55, 150, 20);
    //     replayLastProjectileButton.addActionListener(e -> replayLastProjectile(gp));
    //     this.add(replayLastProjectileButton);
    // }

    // // MODIFIES: gp
    // // EFFECTS: Launches the last Projectile in gp's ProjectileList again
    // private void replayLastProjectile(GamePanel gp) {
    //     List<Projectile> projectiles = gp.getProjectiles().getProjectiles();
    //     if (!projectiles.isEmpty()) {
    //         gp.launchProjectile();
    //     }
    // }
}
