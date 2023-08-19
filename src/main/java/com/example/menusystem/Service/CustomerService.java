package com.example.menusystem.Service;

import com.example.menusystem.Api.ApiException;
import com.example.menusystem.Model.Coupon;
import com.example.menusystem.Model.Customer;
import com.example.menusystem.Model.Menu;
import com.example.menusystem.Reposetry.CouponReposetry;
import com.example.menusystem.Reposetry.CustomerReposetry;
import com.example.menusystem.Reposetry.MenuReposetry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerReposetry customerreposetry;
    private final CouponReposetry couponreposetry;
//    private final MenuService menuservice;
    private final MenuReposetry menureposetry;

    public List<Customer> getAllCustomers() {
        return customerreposetry.findAll();
    }

    public void addCustomer(Customer customer) {
        customerreposetry.save(customer);
    }

    public void removeCustomer(Integer customerId){
        var customer = customerreposetry.findById(customerId);
        if(customer == null){
            throw new ApiException("Customer not found");
        }
        customerreposetry.deleteById(customerId);
    }

    public void updateCustomer( Integer customerId,Customer customer){
        Customer customers = customerreposetry.findCustomerByCustomerId(customerId);
        if(customers == null){
            throw new ApiException("Customer not found");
        }
        customers.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
        customers.setCustomerName(customer.getCustomerName());
        customers.setCustomerBalance(customers.getCustomerBalance());
        customerreposetry.save(customers);
    }

    public Customer findCustomerByName(String customer){
        Customer customer1 = customerreposetry.findCustomerByCustomerName(customer);
        if(customer1 == null){
            throw new ApiException("Customer not found");
        }
        return customer1;
    }

    public void useCoupon(Integer customerId ,Integer id){
        Coupon coupon = couponreposetry.findCouponById(id);
        Customer customers = customerreposetry.findCustomerByCustomerId(customerId);
        if(customers == null){
            throw new ApiException("Customer not found");
        }
       else if(coupon == null){
           throw new ApiException("Coupon not found");
        }
       else if(coupon.getStatus().equals("active")){
           customers.setCustomerBalance(coupon.getCouponPrice());
           customerreposetry.save(customers);
           coupon.setStatus("used");
           couponreposetry.save(coupon);
        }
       else {
           throw new ApiException("Coupon is used");
        }
    }

    public void buyProduct(Integer customerId ,String productName){
        Customer customers = customerreposetry.findCustomerByCustomerId(customerId);
        if (customers == null){
            throw new ApiException("Customer not found");
        }
        Menu menus = menureposetry.findMenuByProductName(productName);
        if(menus == null){
            throw new ApiException("Product not found");
        }
        if(menus.getProductCount() == 0){
            throw new ApiException("Product out of Stock");
        }
        else if(customers.getCustomerBalance() >= menus.getProductPrice()){
            double newBalance = customers.getCustomerBalance() - menus.getProductPrice();
            Integer newCount = menus.getProductCount() - 1 ;
            customers.setCustomerBalance(newBalance);
            menus.setProductCount(newCount);
            menureposetry.save(menus);
        }

    }


}
