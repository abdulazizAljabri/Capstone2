package com.example.menusystem.Controller;

import com.example.menusystem.Api.ApiResponse;
import com.example.menusystem.Model.Customer;
import com.example.menusystem.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerControoler {
    private final CustomerService customerservice;

    @GetMapping("/")
    public ResponseEntity getCustomers(){
        return ResponseEntity.status(HttpStatus.OK).body(customerservice.getAllCustomers());
    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody @Valid Customer customer){
        customerservice.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("added successfully"));
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable Integer customerId){
        customerservice.removeCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("deleted successfully"));
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity updateCustomer( @PathVariable Integer customerId , @RequestBody @Valid Customer customer){
        customerservice.updateCustomer(customerId, customer);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("update successfully"));
    }

    @GetMapping("/findcustomer/{customerName}")
    public ResponseEntity findCustomer(@PathVariable String customerName){
        return ResponseEntity.status(HttpStatus.OK).body(customerservice.findCustomerByName(customerName));
    }


    @GetMapping("/usecoupon/{customerId}/{couponId}")
    public ResponseEntity useCoupon( @PathVariable Integer customerId ,Integer couponId){
        customerservice.useCoupon(customerId, couponId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("successfully used"));
    }

    @GetMapping("/buy/{customerId}/{productName}")
    public ResponseEntity buy( @PathVariable Integer customerId ,@PathVariable String productName){
        customerservice.buyProduct(customerId, productName);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(" Done .. "));
    }

}
