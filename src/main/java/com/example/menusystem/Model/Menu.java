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
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;
    @NotEmpty(message = "should be not  empty")
    @Column(columnDefinition = "varchar(10) NOT NULL unique")
    private String productName;
    @Column(columnDefinition = "varchar(15) NOT NULL check(category = 'drinks' or category = 'meals' or category = 'sweets')")
    private String category;
    @Column(columnDefinition = "int ")
    private Integer productCount;

    @NotNull(message = "should be not null")
    @Column(columnDefinition = "int not null")
    private Double productPrice;


}
