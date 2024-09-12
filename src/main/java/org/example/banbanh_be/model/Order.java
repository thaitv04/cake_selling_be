package org.example.banbanh_be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "order_cake")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int price;

    @ManyToOne
    private User user;

    @ManyToOne
    private Cake cake;


    private int quantity;
    private LocalDateTime createdAt;
}
