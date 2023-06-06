package com.coltis.ecomerce.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class product {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String name;

    private Double price;

    private Integer amount;

    private String description;

    private String imageUrl;
	
}
