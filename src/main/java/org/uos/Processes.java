package org.uos;

import java.io.*;
import java.util.*;
class Processes {
    public static ArrayList<List<String>> records = new ArrayList<>();

    /**
     * File parser to read each line from the csv.
     * Uses Buffered Reader for speed and memory efficiency
     * Grab each line of CSV, stuff into array list.
     * First before looping over file will be the headers row
     * Stuff into own list so can be repeatedly called and matched to lines when printing
     * Print each line of CSV matching the index of headers array list to data
     **/
    public void parseFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            records.clear();

            while ((line = br.readLine()) != null) {
                List<String> fileItem = Arrays.asList(line.split(","));

                records.add(fileItem);
            }
            for (List<String> record : records) {
                for (int j = 0; j < 5; j++) {
                    record.set(j, record.get(j).replace(" ", ""));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     addItem() takes user input and creates an itemsToAdd object populated with data.
     **/
    public void addItem() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter item description: ");

        String description = keyboard.nextLine();
        System.out.println("Enter item unit price: ");

        float unitPrice = keyboard.nextFloat();
        System.out.println("Enter item quantity in stock: ");

        float qtyInStock = keyboard.nextFloat();
        float totalPrice = unitPrice * qtyInStock;
        keyboard.nextLine();
        Record itemsToAdd = new Record(description, unitPrice, (int) qtyInStock, totalPrice);
        writeItem(itemsToAdd);
    }

    /**
     writeItem() takes a filepath and items record from addItem() and writes it to the items csv file.
     This implements the BufferedWriter and FileWriter methods to open the file and write to it in a
     memory efficient manner.
     **/
    public void writeItem(Record itemsToAdd) {
        String newID = genID();
        String items =  newID + "," +
                itemsToAdd.description + "," +
                itemsToAdd.unitPrice + "," +
                itemsToAdd.qtyInStock + "," +
                itemsToAdd.totalPrice;
        List<String> itemsList = Arrays.asList(items);
        records.add(itemsList);
        System.out.println("New entry " + itemsToAdd.description + " added!\n" + items);
        writeCSV("src/main/resources/items.txt");
    }

    public void writeCSV(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,false))) {
            for (List<String> record : records) {
                bw.write(String.valueOf(record).replace("[", "").replace("]", ""));
                bw.newLine();
            }
            records.clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     Generates an ID for a new entry to the items CSV by reading the records array and extracting the last ID.
     The last ID once extracted is incremented by one and is formatted within the space of 5 zeros.
     Once formatted the ID is then returned.
     **/

     public String genID() {
        String string;
        int num = Integer.parseInt(records.get(records.size() - 1).get(0))+ 1;
        string = String.format("%05d", num);
        return string;
    }

    public int findItem(String itemType) {
        int result = 0;
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).get(1).contains(itemType)) {
                return i;
            } else {
                result = -1;
            }
        }
        return result;
    }

    public void removeItem() {
         //initialise a user input variable
        String userInput;

        System.out.println("Please enter an item name exactly: ");
        //Open scanner to read user input from command line
        Scanner input = new Scanner(System.in);
        userInput = input.nextLine();

        //find index of record that matches user input, report if exists and if yes remove it.
        int findItemResult = findItem(userInput);
        if (findItemResult == -1) {
            System.out.println("Item not found");
        } else {
            System.out.println("Removing Record: " + records.get(findItemResult));
            records.remove(findItemResult);
            writeCSV("src/main/resources/items.txt");
        }
     }

     public void updateQuantity() {
         String itemName;
         int quantity;
         int changeFlag;
         int changeResult = 0;
         int currentQuantity;

         System.out.println("Update item quantity");

         //Open scanner to read user input from command line
         Scanner input = new Scanner(System.in);

         System.out.println("Please enter an item name exactly: ");
         itemName = input.nextLine();

         int findItemResult = findItem(itemName);
         if (findItemResult == -1) {
             System.out.println("Item not found");
             return;
         }

         System.out.println("1 to Add quantity, 2 to remove quantity");
         changeFlag = input.nextInt();

         System.out.println("Enter quantity");
         quantity = (int) input.nextFloat();

         currentQuantity = (int) Double.parseDouble(records.get(findItemResult).get(3));

         if (changeFlag == 1) {
             changeResult = currentQuantity + quantity;
         } else if (changeFlag == 2) {
             changeResult = currentQuantity - quantity;
         } else {
             System.out.println("Error, enter a number between 1 and 2");
         }

         if (changeResult < 0) {
             System.out.println("Error, quantity change goes below 0");
         } else {
             System.out.println("Changing quantity from " + currentQuantity + " to " + changeResult);
             records.get(findItemResult).set(3, String.valueOf(changeResult));
             writeCSV("src/main/resources/items.txt");
         }
     }
}

