package com.example.menusystem.Controller;

import com.example.menusystem.Api.ApiResponse;
import com.example.menusystem.Model.Coupon;
import com.example.menusystem.Service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponCotroller {
    private final CouponService couponservice;

    @GetMapping("/")
    public ResponseEntity getCoupons(){
        return ResponseEntity.status(HttpStatus.OK).body(couponservice.getCoupons());
    }
    @PostMapping("/add")
    public ResponseEntity addCoupon(@RequestBody @Valid Coupon coupon){
        couponservice.addCoupon(coupon);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Coupon added"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity removeCoupon(@PathVariable Integer id){
        couponservice.removeCoupon(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Coupon deleted"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCoupon( @PathVariable Integer id,@RequestBody @Valid Coupon coupon){
        couponservice.update(id,coupon);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Coupon updated"));
    }

    @GetMapping("/endcoupons/{id}")
    public ResponseEntity endCoupons(@PathVariable Integer id){
        couponservice.endCoupons(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Coupon is ended"));
    }
}
