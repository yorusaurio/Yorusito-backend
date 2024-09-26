package com.yorusito.admin.domain.model;

public class Statistics {
    private long totalOrders;
    private double totalSales;
    private long totalProducts;

    // Constructor
    public Statistics(long totalOrders, double totalSales, long totalProducts) {
        this.totalOrders = totalOrders;
        this.totalSales = totalSales;
        this.totalProducts = totalProducts;
    }

    // Getters and setters
    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }
}
