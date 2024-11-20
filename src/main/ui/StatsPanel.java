package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Projectile;

public class StatsPanel extends JPanel {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 100;
    private static final Dimension LBLDIMENSION = new Dimension(300, 50);
    private static final String MAXHEIGHTTXT = "Maximum Height Reached: ";
    private static final String FLIGHTTIMETXT = "Total Flight Time: ";
    private Projectile p;

    private JLabel maxHeightLbl;
    private JLabel flightTimeLbl;

    public StatsPanel(GamePanel gp) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.DARK_GRAY);

        p = gp.getProjectile();
        maxHeightLbl = new JLabel(MAXHEIGHTTXT + p.maxHeight() + " meters");
        maxHeightLbl.setPreferredSize(LBLDIMENSION);
        flightTimeLbl = new JLabel(FLIGHTTIMETXT + p.flightTime() + " seconds");
        flightTimeLbl.setPreferredSize(LBLDIMENSION);
        add(maxHeightLbl);
        add(Box.createHorizontalStrut(10));
        add(flightTimeLbl);
    }
}
