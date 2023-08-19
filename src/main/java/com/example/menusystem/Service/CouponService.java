package com.example.menusystem.Service;

import com.example.menusystem.Api.ApiException;
import com.example.menusystem.Model.Coupon;
import com.example.menusystem.Reposetry.CouponReposetry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponReposetry couponreposetry;


    public List<Coupon> getCoupons() {
        return couponreposetry.findAll();
    }

    public void addCoupon(Coupon coupon){
        couponreposetry.save(coupon);
    }

    public void removeCoupon(Integer id){
        Coupon coupon = couponreposetry.findCouponById(id);
        if(coupon == null){
            throw  new ApiException("Coupon not found");
        }
        couponreposetry.deleteById(id);
    }


    public void update (Integer id,Coupon coupon){
        Coupon coupons = couponreposetry.findCouponById(id);
        if(coupons == null){
            throw  new ApiException("Coupon not found");
        }
        coupons.setStatus(coupon.getStatus());
        coupons.setCouponPrice(coupon.getCouponPrice());
    }

    public void changeStatus (Integer id){
        Coupon coupons = couponreposetry.findCouponById(id);
        if(coupons == null){
            throw  new ApiException("Coupon not found");
        }
        if(coupons.getStatus() == "used"){
            coupons.setStatus("active");
            couponreposetry.save(coupons);
        }
        else if(coupons.getStatus() == "active"){
            coupons.setStatus("used");
            couponreposetry.save(coupons);
        }

    }

}
