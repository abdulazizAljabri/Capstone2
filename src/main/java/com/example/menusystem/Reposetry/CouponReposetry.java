package com.example.menusystem.Reposetry;

import com.example.menusystem.Model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponReposetry extends JpaRepository<Coupon,Integer> {
   Coupon findCouponById(Integer id);


}
