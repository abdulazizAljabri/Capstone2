package com.example.menusystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    @NotEmpty(message = "should not be empty")
    @Column(columnDefinition = "varchar (20) NOT NULL")
    private String customerName;

    private Double customerBalance = 0.0;

    @NotEmpty(message = "should not be empty")
    @Column(columnDefinition = "varchar(10) NOT NULL unique")
    private String customerPhoneNumber;
}
