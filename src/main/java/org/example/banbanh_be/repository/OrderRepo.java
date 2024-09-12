package org.example.banbanh_be.repository;

import org.example.banbanh_be.model.Cart;
import org.example.banbanh_be.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(int userId);
}
