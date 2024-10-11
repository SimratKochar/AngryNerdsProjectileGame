package ui;

import java.util.Scanner;

import model.Projectile;
import model.ProjectileList;
import model.Target;

/*
 * Projectile Motion Game Console application
 */
public class GameConsole {
    private Projectile projectile;
    private ProjectileList projList;
    private Target target;

    private Scanner input;

    // EFFECTS: runs the game
    public GameConsole() {
        projList = new ProjectileList();
        target = new Target();
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runGame() {
        input = new Scanner(System.in);
        projectile = new Projectile(1, 1);

        System.out.println("The target is at position (" + target.getX() + 
                ", " + (250 - target.getY()) + "). Use kinematics equations to find " + 
                "the initial velocity and angle required to hit the target!");

        while (true) {
            System.out.println("Previously entered velocities: " + projList.listVelocities());
            System.out.println("Previously entered angles: " + projList.listAngles());
            System.out.println("Enter velocity (m/s): ");
            int velocity = input.nextInt();
            System.out.println("Enter angle (degrees): ");
            int angle = input.nextInt();

            projectile = new Projectile(velocity, angle);
            projList.addProjectile(projectile);

            System.out.println("Maximum height reached: " + projectile.maxHeight() + " meters");
            System.out.println("Approximate flight time: " + projectile.flightTime() + " seconds");

            moveProjectile();

        }
    }

    // MODIFIES: projectile
    // EFFECTS: updates the projectile's position with time
    //          quits the program projectile has hit the target
    //          otherwise continues the program if projectile hits ground before target
    public void moveProjectile() {
        double time = 0.1;
        while (!projectile.groundHit()) {
            projectile.updateCoordinates((float) time);
            time += 0.1;

            if (projectile.targetHit(target)) {
                System.out.println("Congratulations, you hit the target!");
                System.exit(0);
            }
        }
        System.out.println("You missed the target, try again!");

    }
}
