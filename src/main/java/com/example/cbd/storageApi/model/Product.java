package com.example.cbd.storageApi.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

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
    private Long uuid;


    @Column
    private String name;


    public Product(String name) {
        this.name = name;
        this.uuid = 1L;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(uuid, product.uuid) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name);
    }
}


