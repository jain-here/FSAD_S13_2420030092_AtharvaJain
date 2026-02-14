package com.fsad.hibernate;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();

        // Insert multiple products
        Product p1 = new Product();
        p1.setName("Pen");
        p1.setDescription("Blue ink pen");
        p1.setPrice(10.5);
        p1.setQuantity(100);
        dao.addProduct(p1);

        Product p2 = new Product();
        p2.setName("Notebook");
        p2.setDescription("A4 size notebook");
        p2.setPrice(50.0);
        p2.setQuantity(200);
        dao.addProduct(p2);

        // Retrieve all products
        List<Product> products = dao.getAllProducts();
        System.out.println("All Products:");
        for (Product p : products) {
            System.out.println(p);
        }

        // Retrieve by id
        Product prod = dao.getProduct(p1.getId());
        System.out.println("\nRetrieved by ID: " + prod);

        // Update price and quantity
        prod.setPrice(12.0);
        prod.setQuantity(120);
        dao.updateProduct(prod);
        System.out.println("\nAfter update: " + dao.getProduct(prod.getId()));

        // Delete product
        dao.deleteProduct(p2.getId());
        System.out.println("\nAfter deletion:");
        for (Product p : dao.getAllProducts()) {
            System.out.println(p);
        }

        HibernateUtil.shutdown();
    }
}
