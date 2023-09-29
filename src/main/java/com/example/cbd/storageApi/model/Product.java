package com.example.cbd.storageApi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;


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
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

}


