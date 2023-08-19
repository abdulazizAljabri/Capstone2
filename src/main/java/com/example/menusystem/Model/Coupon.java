package com.example.menusystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "should be not empty")
    @Column(columnDefinition = "varchar(10) NOT NULL unique")
    private String couponCode;

    @NotNull(message = "should be not null")
    private Double couponPrice;

    @Column(columnDefinition = "varchar(10) NOT NULL check(status = 'active' or status = 'used')")
    private String status;
}
