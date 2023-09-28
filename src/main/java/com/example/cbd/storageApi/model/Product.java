package com.example.cbd.storageApi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.*;
import lombok. *;
import lombok.experimental.Accessors;



@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Accessors(chain = true)
public class Product implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;


    @Column
    private String name;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @Column
    private String image;


    public Product(String name, String description, BigDecimal price, String image) {
        //this.id = 1L;

        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        //this.id = UUID.randomUUID();
    }

    /*private String String;

    public Product(String name, String description, BigDecimal price, String String) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.String = String;
        this.id = 1L;
    } */

}


