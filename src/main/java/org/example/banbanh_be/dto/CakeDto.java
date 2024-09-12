package org.example.banbanh_be.dto;

import org.example.banbanh_be.model.Cake;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class CakeDto {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private String description;
    private String code;
    private LocalDateTime createdAt;
    private MultipartFile[] image;
    private String typeOfCake;
    private String id_user;

    public String getCode() {
        return code;
    }

    public CakeDto setCode(String code) {
        this.code = code;
        return this;
    }

    public String getTypeOfCake() {
        return typeOfCake;
    }

    public void setTypeOfCake(String typeOfCake) {
        this.typeOfCake = typeOfCake;
    }

    public int getTypeIdCake() {
        if (typeOfCake == null || typeOfCake.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(typeOfCake);
    }

    public CakeDto setTypeIdCake(String typeOfCake) {
        this.typeOfCake = typeOfCake;
        return this;
    }

    public int getId_user() {
        if (id_user == null || id_user.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(id_user);
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public Cake toCake(){
        Cake cake=new Cake();
        cake.setId(id);
        cake.setName(name);
        cake.setPrice(price);
        cake.setQuantity(quantity);
        cake.setDescription(description);
        cake.setCreatedAt(createdAt);
        cake.setCode(code);
        return cake;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public CakeDto setName(String name) {
        this.name = name;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public CakeDto setPrice(int price) {
        this.price = price;
        return this;
    }



    public int getQuantity() {
        return quantity;
    }

    public CakeDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CakeDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CakeDto setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public MultipartFile[] getImage() {
        return image;
    }

    public void setImage(MultipartFile[] image) {
        this.image = image;
    }
}
