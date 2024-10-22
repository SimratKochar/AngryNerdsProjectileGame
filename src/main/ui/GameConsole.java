package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.Projectile;
import model.ProjectileList;
import model.Target;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * Projectile Motion Game Console application
 */
public class GameConsole {
    private static final String JSON_STORE = "./data/gameProgress.json";
    private Projectile projectile;
    private ProjectileList projList;
    private Target target;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the game
    public GameConsole() throws FileNotFoundException{
        projList = new ProjectileList();
        target = new Target();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runGame() {
        Boolean keepGoing = true;
        makeGame();

        System.out.println("The target is at position (" + target.getX() 
                + ", " + (250 - target.getY()) + "). Use kinematics equations to find "
                + "the initial velocity and angle required to hit the target!");

        while (keepGoing) {
            System.out.println("Previously entered velocities: " + projList.listVelocities());
            System.out.println("Previously entered angles: " + projList.listAngles());
            
            inputParameters();

            System.out.println("Maximum height reached: " + projectile.maxHeight() + " meters");
            System.out.println("Approximate flight time: " + projectile.flightTime() + " seconds");

            moveProjectile();

            continueOrSave();
        }
    }

    // MODIFIES: projectile
    // EFFECTS: updates the projectile's position with time
    //          quits the program projectile has hit the target
    //          otherwise continues the program if projectile hits ground before target
    private void moveProjectile() {
        double time = 0.1;
        while (!projectile.groundHit()) {
            projectile.updateCoordinates((float) time);
            time += 0.1;

            if (projectile.targetHit(target)) {
                System.out.println("Congratulations, you hit the target!");
                System.exit(0);
            }
        }
        System.out.println("You missed the target, would you like to try again?");

    }

    // EFFECTS: asks user if they want to play a new game or load a saved game
    private void makeGame() {
        input = new Scanner(System.in);

        System.out.println("Enter 'n' to create a new game");
        System.out.println("Enter 'l' to load a saved game.");

        String command = input.next();

        if (command.equals("n")) {
            return;
        } else if (command.equals("l")) {
            loadSavedGame();
        } else {
            System.out.println("Invalid input, please try again.");
            makeGame();
        }
    }

    private void inputParameters() {
        input = new Scanner(System.in);
        projectile = new Projectile(1, 1);

        System.out.println("Enter velocity (m/s): ");
        int velocity = input.nextInt();
        System.out.println("Enter angle (degrees): ");
        int angle = input.nextInt();

        projectile = new Projectile(velocity, angle);
        projList.addProjectile(projectile);
    }

    // Ask for the user's input to save and quit or try again
    private void continueOrSave() {
        input = new Scanner(System.in);

        System.out.println("Enter 'y' to try again.");
        System.out.println("Enter 's' to save your progress and quit.");

        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("y")) {
            return;
        } else if (command.equals("s")) {
            saveProgress();
            System.exit(0);
        } else {
            System.out.println("Invalid input. Please enter 'y' or 's'.");
            continueOrSave();
        }
    }

    private void saveProgress() {
        try {
            jsonWriter.open();
            jsonWriter.write(target, projList);
            jsonWriter.close();
            System.out.println("Saved progress to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: Reads a saved game
    private void loadSavedGame() {
        try {
            target = jsonReader.readTarget();
            projList = jsonReader.readPList();
            System.out.println("Loaded game from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE + ". Please start a new game first.");
            makeGame();
        }
    }

}
