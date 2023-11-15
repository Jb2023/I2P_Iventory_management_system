package org.uos;

/** UNIVERSITY OF SUFFOLK - INTRODUCTION TO PROGRAMMING
  Module assignment

  Module Lead: Dr. Kakia Chatsiou
  Last updated 2022-02-25

  The assignment starter code consists of 3 files:

  a) store.java: this file contains starting code for the inventory
  management control system. See assignment brief document for
  more information on how to build the rest of the application.

  b) items.txt: this file contains a list of all items in the inventory
  with information about their quantities and total price in stock. See
  assignment text for more information.

  c) transactions.txt: this file contains a list of all the transactions
  for the day. You will be using it to print out the report of transactions
  Each time a transaction happens i.e. an item is added or removed,
  a record should be stored in transactions.txt


  You are asked to work on expanding the starter code so that your Java app can do the following:

   - read and output to the 2 files (transactions.txt, items.txt) as appropriate
   - autogenerate a (5-digit) item id ie. 00001 for each new item
   - add a new item to the inventory (by appending a line to items.txt)
  - update the quantity of an item already in store (in items.txt)
 - remove an item from the inventory (by removing relevant entry in items.txt)
 - search for an item in the inventory (items.txt)
 - generate and print a daily transaction report (using transactions.txt)

 Check out the full assignment brief for more information about the report.
 **/
import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class Store {
    String description;
    float unitPrice,qtyInStock,totalPrice;
    /** File parser to read each line from the csv.
    Uses Buffered Reader for speed and memory efficiency
    Grab each line of CSV, stuff into array list.
    First before looping over file will be the headers row
    Stuff into own list so can be repeatedly called and matched to lines when printing
    Print each line of CSV matching the index of headers array list to data **/
    public static void parseFile(String filePath, byte userInput) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            switch (userInput) {
                case 1 -> readFile(br);
                case 2 -> writeItem(br);
                default -> System.out.println("Invalid option");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    /** This method will read the file if the user chooses this option
    It will take the file path,
    **/
    public static void readFile(BufferedReader br) throws IOException {
        String line;
        String[] headers = br.readLine().split(",");
        System.out.println(Arrays.toString(headers));
        while ((line = br.readLine()) != null) {
            String [] values = line.split(",");
            for (byte i = 0; i < headers.length; i ++) {
                System.out.printf("%s: %s, ", headers[i],values[i]);
                if (i == headers.length - 1) {
                    System.out.print("\n");
                }
            }
        }
    }

    /** constructor to populate variables with item data
    can be used for a few things I hope other than writing to files
    otherwise a secure way of handling data.
    **/
    private Store(String description, float unitPrice, float qtyInStock, float totalPrice) {
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyInStock = qtyInStock;
        this.totalPrice = totalPrice;
    }

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

        Store store = new Store(description, unitPrice, qtyInStock, totalPrice);
    }

    public static String genID (String filePath) throws IOException {
        ReversedLinesFileReader fr = new ReversedLinesFileReader(new File(filePath));
        String[] lastLine = fr.readLine().split(",");
        return String.format("%05d",Integer.parseInt(lastLine[0] + 1));
    }


    public void writeItem() {
        BufferedWriter bw = new BufferedWriter();
        String[] items = new String[5];
        items[0] = genID();
        items[1] = description;
        items[2] = String.valueOf(unitPrice);
        items[3] = String.valueOf(qtyInStock);
        items[4] = String.valueOf(totalPrice);

        br.newLine();
        br.writeLine(Arrays.toString(items));
    }

}
