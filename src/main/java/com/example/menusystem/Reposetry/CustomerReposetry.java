package com.example.menusystem.Reposetry;

import com.example.menusystem.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerReposetry extends JpaRepository<Customer,Integer> {
    Customer findCustomerByCustomerId(Integer customerId);
    Customer findCustomerByCustomerName(String customerName);







}
