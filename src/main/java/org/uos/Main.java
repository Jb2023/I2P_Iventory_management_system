package org.uos;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        userInterface.renderGUI();
        userChoice();
        System.out.println("\n\n Thanks for using this program...!");
    }

    public static void userChoice() throws FileNotFoundException {
        byte userInput = 0;
        byte userInput2;
        while (userInput > 5 || userInput < 1) {
            Scanner input = new Scanner(System.in);
            System.out.print("\n Enter a choice and Press ENTER to continue[1-5]:");
            userInput = input.nextByte();
            System.out.println("\n second choice");
            userInput2 = input.nextByte();

            switch (userInput) {
                case 1 -> Store.addItem();
                case 2 -> System.out.println("Item Quantity updated");
                case 3 -> System.out.println("Item Removed");
                case 4 -> Store.parseFile("src/main/resources/items.txt", userInput2);
                default -> System.out.println("This is not a valid option, please try again!");
            }
        }
    }


}