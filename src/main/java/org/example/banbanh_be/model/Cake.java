package org.example.banbanh_be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cake")
@Data
public class Cake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private int quantity;
    private String description;
    private LocalDateTime createdAt;
    private String code;
    @OneToMany(mappedBy = "cake",cascade = CascadeType.ALL)
    private List<Image> image;

    @ManyToOne
    private User user;

    @ManyToOne
    private TypeOfCake typeOfCake;



}
