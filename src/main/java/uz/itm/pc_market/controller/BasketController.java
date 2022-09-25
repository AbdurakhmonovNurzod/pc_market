package uz.itm.pc_market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.itm.pc_market.entity.Basket;
import uz.itm.pc_market.loader.BasketLoader;
import uz.itm.pc_market.loader.Result;
import uz.itm.pc_market.service.BasketService;

import java.util.List;

@RestController
@RequestMapping("/basket")
public class BasketController {
    @Autowired
    BasketService basketService;
    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ResponseEntity<Result>addBasket(@RequestBody BasketLoader basketLoader){
        Result result = basketService.addBasket(basketLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);

    }
    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping
    public List<Basket>getAllBaskets(){
       return basketService.getAllBaskets();
    }
    @PreAuthorize(value = "hasAuthority('READ_ONE_PRODUCT')")
    @GetMapping("/{id}")
    public ResponseEntity<Basket>getBasketById(@PathVariable Integer id){
        Basket basketById = basketService.getBasketById(id);
        if (basketById!=null)
            return ResponseEntity.status(HttpStatus.FOUND).body(basketById);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping("/notDelivered")
    public List<Basket>getNotDeliveredBaskets(){
        return basketService.getNotDeliveredBaskets();
    }
    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PutMapping("/{id}")
    public ResponseEntity<Result>editBasket(@PathVariable Integer id,@RequestBody BasketLoader basketLoader){
        Result result = basketService.editBasket(id, basketLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);


    }
    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Result>deleteBasket(@PathVariable Integer id){
        Result result = basketService.deleteBasket(id);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

}
