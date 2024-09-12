package org.example.banbanh_be.controller;

import org.example.banbanh_be.model.Cart;
import org.example.banbanh_be.model.Order;
import org.example.banbanh_be.repository.IUserRepo;
import org.example.banbanh_be.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("apiOrder")
@RequestMapping("/api/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;


    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> findAll(@PathVariable int id) {
        List<Order> orders = orderRepo.findByUserId(id);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
