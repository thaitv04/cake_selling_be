package org.example.banbanh_be.dto;

import org.example.banbanh_be.model.Cake;
import org.example.banbanh_be.model.Cart;
import org.example.banbanh_be.model.User;

import java.time.LocalDateTime;

public class CartDto {
    private int id;
    private int quantity;
    private int id_user;
    private int id_cake;
    private LocalDateTime createdAt;

    public Cart toCart(){
        Cart cart = new Cart();

        cart.setCreatedAt(createdAt);

        cart.setQuantity(quantity);
        return cart;
    }
    public void updateCart(Cart cart, User user, Cake cake) {
        cart.setQuantity(quantity);
        cart.setCreatedAt(createdAt);
        cart.setUser(user);
        cart.setCake(cake);
    }

    public int getId() {
        return id;
    }

    public CartDto setId(int id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CartDto setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public CartDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public int getId_user() {
        return id_user;
    }



    public CartDto setId_user(int id_user) {
        this.id_user = id_user;
        return this;
    }

    public int getId_cake() {
        return id_cake;
    }

    public  CartDto setId_cake(int id_cake) {
        this.id_cake = id_cake;
        return this;
    }
}
