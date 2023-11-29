package org.uos;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class Processes {

    /**
     * File parser to read each line from the csv.
     * Uses Buffered Reader for speed and memory efficiency
     * Grab each line of CSV, stuff into array list.
     * First before looping over file will be the headers row
     * Stuff into own list so can be repeatedly called and matched to lines when printing
     * Print each line of CSV matching the index of headers array list to data
     **/
    public static void parseFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] headers = br.readLine().split(",");
            System.out.println(Arrays.toString(headers));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (byte i = 0; i < headers.length; i++) {
                    System.out.printf(" %s", values[i]);
                    if (i == headers.length - 1) {
                        System.out.print("\n");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     addItem() takes user input and creates an itemsToAdd object populated with data.
     **/
    public static void addItem() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter item description: ");

        String description = keyboard.nextLine();
        System.out.println("Enter item unit price: ");

        float unitPrice = keyboard.nextFloat();
        System.out.println("Enter item quantity in stock: ");

        float qtyInStock = keyboard.nextFloat();
        float totalPrice = unitPrice * qtyInStock;
        keyboard.nextLine();

        Record itemsToAdd = new Record(description, unitPrice, qtyInStock, totalPrice);
        writeItem("src/main/resources/items.txt", itemsToAdd);
    }

    /**
     writeItem() takes a filepath and items record from addItem() and writes it to the items csv file.
     This implements the BufferedWriter and FileWriter methods to open the file and write to it in a
     memory efficient manner.
     **/
    public static void writeItem(String filePath, Record itemsTooAdd) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true))) {
            String newID = genID(filePath);
            String items =  newID + "," +
                    itemsTooAdd.description + "," +
                    itemsTooAdd.unitPrice + "," +
                    itemsTooAdd.qtyInStock + "," +
                    itemsTooAdd.totalPrice;
            System.out.println("New entry " + itemsTooAdd.description + " added!\n" + items);
            bw.newLine();
            bw.write(items);
        } catch (Exception e) {
         e.getStackTrace();
        }
    }

    /**
     Generates an ID for a new entry to the items CSV by reading through it and extracting the last ID.
     The last ID once extracted is incremented by one and is formatted within the space of 5 zeros.
     Once formatted the ID is then returned.
     **/

     public static String genID(String filePath) {
        String string;
        String line;
        String[] lastLine = new String[5];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                lastLine = line.split(",");
            }
            System.out.println(Arrays.toString(lastLine));
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            int num = Integer.parseInt(lastLine[0])+ 1;
            string = String.format("%05d", num);
        }
        return string;
    }

    public static String findItem(String itemType) {
         return null;
    }
}

