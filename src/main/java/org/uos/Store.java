package org.uos;

import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class Store {

    /**
     * File parser to read each line from the csv.
     * Uses Buffered Reader for speed and memory efficiency
     * Grab each line of CSV, stuff into array list.
     * First before looping over file will be the headers row
     * Stuff into own list so can be repeatedly called and matched to lines when printing
     * Print each line of CSV matching the index of headers array list to data
     **/
    public static void parseFile(String filePath) throws FileNotFoundException {
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
     * constructor to populate variables with item data
     * can be used for a few things I hope other than writing to files
     * otherwise a secure way of handling data.
     **/


    public static void addItem() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter item description: ");

        String description = keyboard.nextLine();
        System.out.println("Enter item unit price: ");

        float unitPrice = keyboard.nextFloat();
        System.out.println("Enter item quantity in stock: ");

        float qtyInStock = keyboard.nextFloat();
        float totalPrice = unitPrice * qtyInStock;
        keyboard.nextLine();

        DataStorage itemsToAdd = new DataStorage(description, unitPrice, qtyInStock, totalPrice);
        writeItem("src/main/resources/items.txt");
    }

    public static void writeItem(String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            String[] items = new String[5];
            items[0] = genID(filePath);
            items[1] = itemsToAdd.description;
            items[2] = String.valueOf(unitPrice);
            items[3] = String.valueOf(qtyInStock);
            items[4] = String.valueOf(totalPrice);

            bw.newLine();
            bw.write(Arrays.toString(items));
        } catch (Exception e) {
         e.getStackTrace();
        }
    }

    public static String genID(String filePath) throws IOException {
        ReversedLinesFileReader fr = new ReversedLinesFileReader(new File(filePath));
        String[] lastLine = fr.readLine().split(",");
        return String.format("%05d", Integer.parseInt(lastLine[0] + 1));
    }
}

