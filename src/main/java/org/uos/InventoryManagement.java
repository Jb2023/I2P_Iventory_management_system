package org.uos;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class InventoryManagement {
    static userInterface ui = new userInterface();
    static Processes pr = new Processes();
    static boolean activeSession = true;

    public static void main(String[] args) {userControl();}

    private static void userControl() {
        while (activeSession) {
            ui.renderInterface();
            byte userInput = 0;
            while (userInput > 7 || userInput < 1) {
                Scanner input = new Scanner(System.in);
                userInput = input.nextByte();
            }
            userChoice(userInput);
        }
    }

    public static void userChoice(byte userInput) {
        pr.parseFile("src/main/resources/items.txt", Processes.records);
        pr.parseFile("src/main/resources/transactions.txt", Processes.transactionRecord);
        switch (userInput) {
            case 1 -> pr.addItem();
            case 2 -> pr.updateQuantity();
            case 3 -> pr.removeItem();
            case 4 -> ui.viewInventory(Processes.records);
            case 5 -> ui.viewInventory(Processes.transactionRecord);
            case 6 -> activeSession = false;
            default -> System.out.println("This is not a valid option, please try again!");
        }
    }
}
