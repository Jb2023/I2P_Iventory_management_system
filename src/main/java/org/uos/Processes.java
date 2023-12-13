package org.uos;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
class Processes {
    ArrayList<List<String>> records = new ArrayList<>();

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
            String[] headers = br.readLine().split(",");
            int index = 0;
            System.out.println(Arrays.toString(headers));
            while ((line = br.readLine()) != null) {
                records.add(List.of(line.split(",")));
            }
            System.out.println(records);
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
        System.out.println(description + unitPrice + qtyInStock + totalPrice);
        Record itemsToAdd = new Record(description, unitPrice, qtyInStock, totalPrice);
        writeItem("src/main/resources/items.txt", itemsToAdd);
    }

    /**
     writeItem() takes a filepath and items record from addItem() and writes it to the items csv file.
     This implements the BufferedWriter and FileWriter methods to open the file and write to it in a
     memory efficient manner.
     **/
    public void writeItem(String filePath, Record itemsToAdd) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true))) {
            String newID = genID();
            String items =  newID + "," +
                    itemsToAdd.description + "," +
                    itemsToAdd.unitPrice + "," +
                    itemsToAdd.qtyInStock + "," +
                    itemsToAdd.totalPrice;
            System.out.println("New entry " + itemsToAdd.description + " added!\n" + items);
            bw.newLine();
            bw.write(items);


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
//    public static String findItem(String itemType) {
//         itemType = itemType.toLowerCase(); //forces input consistency with
//         String filePath = "";
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String result;
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                if (Objects.equals(values[1], itemType)) {
//                    result  = Arrays.toString(values);
//                } else {
//                    result = "Item not found";
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//
//        }
//        return result;
//    }
}

