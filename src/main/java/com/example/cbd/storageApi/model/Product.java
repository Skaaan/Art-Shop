package com.example.cbd.storageApi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.example.cbd.externalApi.model.PexelsImage;
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
    @Column(nullable = false, unique = true)
    private Long id;


    @Column
    private String name;

    private String description;

    private BigDecimal price;


    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = 1L;
    }

    /*private PexelsImage pexelsImage;

    public Product(String name, String description, BigDecimal price, PexelsImage pexelsImage) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.pexelsImage = pexelsImage;
        this.id = 1L;
    } */



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}


