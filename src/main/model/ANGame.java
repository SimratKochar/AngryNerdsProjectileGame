package model;

import java.awt.Color;
import java.awt.Graphics;

public class ANGame {
    private Projectile projectile;
    private ProjectileList projList;
    private Target target;

    private boolean isWallHit;
    private boolean isTargetHit;

    private float time;


    // EFFECTS: Creates a new AngryNerds game
    public ANGame() {
        this.projectile = new Projectile(0, 0);
        this.projList = new ProjectileList();
        this.target = new Target();
        isWallHit = false;
        isTargetHit = false;
    }

   // MODIFIES: this
   // EFFECTS: Makes p the current projectile and adds it to ProjectileList
    public void initProjectile(Projectile p) {
        this.projectile = p;
        this.projList.addProjectile(p);
    }

    // MODIFIES: this
    // EFFECTS: resets the game state and prepares for launch
    public void launchProjectile() {
        this.isTargetHit = false;
        this.isWallHit = false;
        this.time = (float) 0.1;
    }

    // Updates the panel and projetile's coordinates on clock tick
    // MODIFIES: this, p
    // EFFECTS: advances time, projectile's coordinates while checking if the
    // projectile has hit the wall or target
    public void update() {
        if (!isWallHit && !isTargetHit) {
            this.projectile.updateCoordinates(time);
            time += 0.1;
        }
        checkProjectileCollision();
    }

    // Called when projectile is removed
    // MODIFIES: this
    // EFFECTS: Resets the collision state of projectile
    public void setWallHit() {
        this.isTargetHit = false;
        this.isWallHit = true;
    }

    // MODIFIES: g
    // EFFECTS: draws the projectile and target onto g
    public void draw(Graphics g) {
        drawProjectile(g);
        drawTarget(g);
    }

    // Draws the projectile
    // MODIFIES: g
    // EFFECTS: Draws the projectile onto g
    private void drawProjectile(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(Projectile.COLOR);
        g.fillOval((int) projectile.getX() - Projectile.SIZE / 2,
                (int) projectile.getY() - Projectile.SIZE / 2,
                Projectile.SIZE, Projectile.SIZE);
        g.setColor(savedCol);
    }

    // Draws the target
    // MODIFIES: g
    // EFFECTS: Draws the target onto g
    private void drawTarget(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(Target.COLOR);
        g.fillOval(target.getX(), target.getY(), Target.SIZE, Target.SIZE);
        g.setColor(savedCol);
    }

    // EFFECTS: updates the collision status of projectile based on its position
    //          and returns true if it has collided with target or wall
    public boolean checkProjectileCollision() {
        if (!projList.getProjectiles().isEmpty()) {
            if (projectile.targetHit(this.target)) {
                isTargetHit = true;
                return true;
            } else if (projectile.groundHit() || (projectile.getX() > 1000)) {
                isWallHit = true;
                return true;
            }
        }
        return false;
    }

    // REQUIRES: A valid ProjectileList
    // MODIFIES: this
    // EFFECTS: Updates the panel's ProjectileList to projList
    public void setProjectiles(ProjectileList projList) {
        this.projList = projList;
    }

    // REQUIRES: A valid Projectile
    // MODIFIES: this
    // EFFECTS: Updates the panel's Projectile to p
    public void setProjectile(Projectile p) {
        this.projectile = p;
    }

    // REQUIRES: A valid Target
    // MODIFIES: this
    // EFFECTS: Updates the panel's Target to t
    public void setTarget(Target t) {
        this.target = t;
    }

    // EFFECTS: Returns if the projectile has hit the wall
    public boolean isWallHit() {
        return this.isWallHit;
    }

    // EFFECTS: Returns if the projectile has hit the target
    public boolean isTargetHit() {
        return this.isTargetHit;
    }

    // EFFECTS: Returns the list of projectiles
    public ProjectileList getProjectiles() {
        return this.projList;
    }

    // EFFECTS: Returns the current projectile
    public Projectile getProjectile() {
        return this.projectile;
    }

    // EFFECTS: returns the current target
    public Target getTarget() {
        return this.target;
    }
}
