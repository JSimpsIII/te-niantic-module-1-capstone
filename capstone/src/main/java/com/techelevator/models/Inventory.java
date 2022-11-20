package com.techelevator.models;

import com.techelevator.models.products.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {

    //create new arraylist for product inventory
    private List<Product> products = new ArrayList<>();

    //getter
    public List<Product> getProducts() {
        return products;
    }

    //empty constructor
    public Inventory() {

    }

    //method to write inventory from .csv file to list
    public void loadInventory() {

        products = new ArrayList<>();

        File productsFile = new File("data/vendingmachine.csv");
        try(Scanner reader = new Scanner(productsFile)) {

            while (reader.hasNextLine()) {

                String line = reader.nextLine();
                String[] columns = line.split("\\|");

                String id = columns[0];
                String name = columns[1];
                double price = Double.parseDouble(columns[2]);
                String productType = columns[3];

                Product product = new Product(id, name, price, productType);
                products.add(product);
            }

        } catch (FileNotFoundException e) {
            System.out.println("logging file not found exception");
        }

    }

}
