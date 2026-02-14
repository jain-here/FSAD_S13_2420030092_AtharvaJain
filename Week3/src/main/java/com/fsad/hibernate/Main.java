package com.fsad.hibernate;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();

        // Insert multiple products (add more for HQL tasks)
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

        // Add more products for HQL tasks
        Product p3 = new Product();
        p3.setName("Pencil");
        p3.setDescription("HB pencil");
        p3.setPrice(5.0);
        p3.setQuantity(300);
        dao.addProduct(p3);

        Product p4 = new Product();
        p4.setName("Eraser");
        p4.setDescription("White eraser");
        p4.setPrice(3.0);
        p4.setQuantity(150);
        dao.addProduct(p4);

        Product p5 = new Product();
        p5.setName("Marker");
        p5.setDescription("Black marker");
        p5.setPrice(25.0);
        p5.setQuantity(80);
        dao.addProduct(p5);

        Product p6 = new Product();
        p6.setName("Sharpener");
        p6.setDescription("Metal sharpener");
        p6.setPrice(7.0);
        p6.setQuantity(120);
        dao.addProduct(p6);

        Product p7 = new Product();
        p7.setName("Scale");
        p7.setDescription("30cm scale");
        p7.setPrice(15.0);
        p7.setQuantity(60);
        dao.addProduct(p7);

        // Retrieve all products
        List<Product> products = dao.getAllProducts();
        System.out.println("All Products:");
        for (Product p : products) {
            System.out.println(p);
        }

        // HQL Queries for Week 3
        System.out.println("\nProducts sorted by price (asc):");
        for (Product p : dao.getProductsSortedByPriceAsc()) {
            System.out.println(p);
        }

        System.out.println("\nProducts sorted by price (desc):");
        for (Product p : dao.getProductsSortedByPriceDesc()) {
            System.out.println(p);
        }

        System.out.println("\nProducts sorted by quantity (desc):");
        for (Product p : dao.getProductsSortedByQuantityDesc()) {
            System.out.println(p);
        }

        System.out.println("\nFirst 3 products (pagination):");
        for (Product p : dao.getProductsPaginated(0, 3)) {
            System.out.println(p);
        }

        System.out.println("\nNext 3 products (pagination):");
        for (Product p : dao.getProductsPaginated(3, 3)) {
            System.out.println(p);
        }

        System.out.println("\nTotal number of products: " + dao.countTotalProducts());
        System.out.println("Products with quantity > 0: " + dao.countProductsWithQuantityGreaterThanZero());

        System.out.println("\nCount products grouped by description:");
        for (Object[] row : dao.countProductsGroupedByDescription()) {
            System.out.println("Description: " + row[0] + ", Count: " + row[1]);
        }

        Object[] minMax = dao.findMinAndMaxPrice();
        System.out.println("\nMin price: " + minMax[0] + ", Max price: " + minMax[1]);

        System.out.println("\nGroup by description:");
        for (Object[] row : dao.groupByDescription()) {
            System.out.println("Description: " + row[0] + ", Count: " + row[1]);
        }

        System.out.println("\nProducts with price between 5 and 20:");
        for (Product p : dao.filterByPriceRange(5, 20)) {
            System.out.println(p);
        }

        System.out.println("\nNames starting with 'P':");
        for (Product p : dao.nameStartsWith("P")) {
            System.out.println(p);
        }

        System.out.println("\nNames ending with 'r':");
        for (Product p : dao.nameEndsWith("r")) {
            System.out.println(p);
        }

        System.out.println("\nNames containing 'a':");
        for (Product p : dao.nameContains("a")) {
            System.out.println(p);
        }

        System.out.println("\nNames with exact length 6:");
        for (Product p : dao.nameWithExactLength(6)) {
            System.out.println(p);
        }

        HibernateUtil.shutdown();
    }
}
