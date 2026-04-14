package com.uwlFood.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodId;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private String foodCategory;

    @Column(nullable = false)
    private BigDecimal foodPrice;


}
