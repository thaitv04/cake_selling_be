package org.example.banbanh_be.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.banbanh_be.dto.CartDto;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;


    @ManyToOne
    private Cake cake;


}
