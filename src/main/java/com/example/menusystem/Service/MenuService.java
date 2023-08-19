package com.example.menusystem.Service;

import com.example.menusystem.Api.ApiException;
import com.example.menusystem.Model.Menu;
import com.example.menusystem.Reposetry.MenuReposetry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuReposetry menureposetry;

    public List<Menu> getMenus() {
        return menureposetry.findAll();
    }

    public void addMenu(Menu menu) {
        menureposetry.save(menu);
    }

    public void deleteMenu(Integer id) {
        Menu menu = menureposetry.findByMenuId(id);
        if (menu == null) {
            throw new ApiException("Menu not found");
        }
        menureposetry.delete(menu);
    }

    public void updateMenu(Integer id, Menu menu) {
        Menu menus = menureposetry.findByMenuId(id);
        if (menus == null) {
            throw new ApiException("Menu not found");
        }
        menus.setProductName(menu.getProductName());
        menus.setCategory(menu.getCategory());
        menus.setProductPrice(menu.getProductPrice());
        menureposetry.save(menus);
    }

    public List<Menu> findByCategory(String category) {
        List<Menu> menus = menureposetry.findByCategory(category);
        if (menus.isEmpty()) {
            throw new ApiException("Category not found");
        }
        return menus;
    }

    public List<Menu> findCountProducts(Integer productCount){
        List<Menu> menus = menureposetry.findByProductCount(productCount);
        return menus;
    }

    public List<Menu> findProductsPrice(Double productPrice){
        List<Menu> menus = menureposetry.findMenuByProductPrice(productPrice);
        if(menus.isEmpty()) {
            throw new ApiException("we not have any products with this price");
        }
        return menus;
    }

    public void findProductsAfterdiscount(String productName){
       Menu menu = menureposetry.findMenuByProductName(productName);
       if(menu == null){
           throw new ApiException("product not found");
       }
       double newPrice = menu.getProductPrice() * 0.1;
       menu.setProductPrice(newPrice);
       menureposetry.save(menu);
    }


}
