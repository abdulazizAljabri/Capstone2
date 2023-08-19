package com.example.menusystem.Controller;

import com.example.menusystem.Api.ApiResponse;
import com.example.menusystem.Model.Menu;
import com.example.menusystem.Service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuservice;

    @GetMapping("/")
    public ResponseEntity getMenu() {
        return ResponseEntity.status(HttpStatus.OK).body(menuservice.getMenus());
    }


    @PostMapping("/add")
    public ResponseEntity addMenu(@RequestBody @Valid Menu menu) {
        menuservice.addMenu(menu);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("menu added"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity removeMenu(@PathVariable Integer id) {
        menuservice.deleteMenu(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("menu removed"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMenu(@PathVariable Integer id, @RequestBody @Valid Menu menu) {
        menuservice.updateMenu(id, menu);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("menu updated"));
    }

    @GetMapping("/getcategory/{category}")
    public ResponseEntity findCategory(@PathVariable String category) {
        return ResponseEntity.status(HttpStatus.OK).body(menuservice.findByCategory(category));
    }
    @GetMapping("/empty/{productCount}")
    public ResponseEntity countProducts(@PathVariable Integer productCount){
        return ResponseEntity.status(HttpStatus.OK).body(menuservice.findCountProducts(productCount));
    }

    @GetMapping("/getproduct/{productPrice}")
    public ResponseEntity findProductPrice(@PathVariable Double productPrice){
        return ResponseEntity.status(HttpStatus.OK).body(menuservice.findProductsPrice(productPrice));
    }

    @GetMapping("/discount/{productName}")
    public ResponseEntity findDiscount(@PathVariable String productName){
        menuservice.findProductsAfterdiscount(productName);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("discount done"));
    }

}
