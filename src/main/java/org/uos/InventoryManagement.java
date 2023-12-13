package org.uos;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class InventoryManagement {
    static boolean activeSession = true;
    static Processes process = new Processes();
    public static void main(String[] args) {
        userControl();
    }

    public static void userControl() {

        while (activeSession) {
            userInterface ui = new userInterface();
            ui.renderInterface();

            byte userInput = 0;
            while (userInput > 6 || userInput < 1) {
                Scanner input = new Scanner(System.in);
                userInput = input.nextByte();
            }
            userChoice(userInput);
        }
    }

    public static void userChoice(byte userInput) {
        process.parseFile("src/main/resources/items.txt");
        switch (userInput) {
            case 1 -> process.addItem();
            case 2 -> System.out.println("Item Quantity updated");
            case 3 -> System.out.println("Item Removed");
            case 4 -> System.out.println("Test");
            case 5 -> activeSession = false;
            default -> System.out.println("This is not a valid option, please try again!");
        }
    }
}
