package org.example.banbanh_be.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "typeOfCake")
@Entity
public class TypeOfCake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

}
