package com.yorusito.admin.domain.service;

import com.yorusito.admin.domain.model.Statistics;
import com.yorusito.ordermanagement.domain.repository.OrderRepository;
import com.yorusito.inventory.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public AdminService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // Obtener estadísticas de ventas y productos
    public Statistics getStatistics() {
        long totalOrders = orderRepository.count();
        double totalSales = orderRepository.getTotalSales();
        long totalProducts = productRepository.count();
        return new Statistics(totalOrders, totalSales, totalProducts);
    }
}
