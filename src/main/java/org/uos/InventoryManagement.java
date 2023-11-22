package org.uos;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class InventoryManagement {


    public static void main(String[] args) {
        userInterface.userControl();
    }

    public static void userChoice(byte userInput) {
            switch (userInput) {
                case 1 -> Store.addItem();
                case 2 -> System.out.println("Item Quantity updated");
                case 3 -> System.out.println("Item Removed");
                case 4 -> Store.parseFile("src/main/resources/items.txt");
                default -> System.out.println("This is not a valid option, please try again!");
            }
        }
    }