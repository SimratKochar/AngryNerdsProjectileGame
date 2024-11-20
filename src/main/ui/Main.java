package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new AngryNerds();
            System.out.println("Welcome to the Projectile Motion Game!");
            new GameConsole();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
        
    }
}
