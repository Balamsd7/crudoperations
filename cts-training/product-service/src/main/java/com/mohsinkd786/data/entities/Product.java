package com.mohsinkd786.data.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String productId = UUID.randomUUID().toString();
    private String name;
    private double price;
    private String sku;

}
