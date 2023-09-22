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
    //@GeneratedValue(generator = "")
    //@GenericGenerator(name = "", strategy = "")
    @Column(nullable = false, unique = true)
    private Long id;


    @Column
    private String name;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @Column
    private PexelsImage image;


    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = 1L;
        //this.id = UUID.randomUUID();
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
        return Objects.equals(this.id, product.id) && Objects.equals(this.name, product.name) && Objects.equals(this.description, product.description)
                && Objects.equals(this.price, product.price);
        //todo compare image -> image
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}


