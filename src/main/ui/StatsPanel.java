package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Projectile;

/*
 * Represents the StatsPanel which displays the maximum height and flight time
 * of the current projectile in GamePanel
 */
public class StatsPanel extends JPanel {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 100;
    private static final Dimension LBLDIMENSION = new Dimension(300, 50);
    private static final String MAXHEIGHTTXT = "Maximum Height Reached: ";
    private static final String FLIGHTTIMETXT = "Total Flight Time: ";
    private Projectile projectile;

    private JLabel maxHeightLbl;
    private JLabel flightTimeLbl;

    // Constructs a new StatsPanel
    // EFFECTS: Sets the size and background color and adds labels
    public StatsPanel(GamePanel gp) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GRAY);

        addLabels(gp);
    }

    // MODIFIES: this
    // EFFECTS: Adds and initializes the panels with values of projectile from gp
    private void addLabels(GamePanel gp) {
        projectile = gp.getProjectile();
        maxHeightLbl = new JLabel(MAXHEIGHTTXT + projectile.maxHeight() + " meters");
        maxHeightLbl.setPreferredSize(LBLDIMENSION);
        flightTimeLbl = new JLabel(FLIGHTTIMETXT + projectile.flightTime() + " seconds");
        flightTimeLbl.setPreferredSize(LBLDIMENSION);
        add(maxHeightLbl);
        add(flightTimeLbl);
    }

    // MODIFIES: this
    // EFFECTS: Updates the maximum height label and flight time label with values
    // for the latest projectile
    public void update(GamePanel gp) {
        remove(maxHeightLbl);
        remove(flightTimeLbl);

        addLabels(gp);

        repaint();
    }
}
