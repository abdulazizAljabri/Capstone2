package com.example.menusystem.Reposetry;

import com.example.menusystem.Model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuReposetry  extends JpaRepository<Menu,Integer> {

    Menu findByMenuId(Integer menuId);

    List<Menu> findByCategory(String category);

    Menu findMenuByProductName(String productName);

    List<Menu> findMenuByProductPrice(Double productPrice);

    @Query("select p from Menu p where p.productCount=?1")
    List<Menu> findByProductCount(Integer productCount);






}
