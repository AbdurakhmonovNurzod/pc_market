package uz.itm.pc_market.controller;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.itm.pc_market.entity.Product;
import uz.itm.pc_market.loader.ProductLoader;
import uz.itm.pc_market.loader.Result;
import uz.itm.pc_market.service.ProductService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Result>addProduct(@RequestBody ProductLoader productLoader){
        Result result = productService.addProduct(productLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        Product productById = productService.getProductById(id);
        if (productById!=null)
            return ResponseEntity.status(HttpStatus.FOUND).body(productById);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    @GetMapping("/{categoryId}")
    public List<Product>getProductsByCategoryId(@PathVariable Integer categoryId){
        return productService.getProductsByCategoryId(categoryId);
    }
    @GetMapping("/withDiscount")
    public List<Product>getProductsWithDiscount(){
        return productService.getAllProductswithDiscount();
    }
    @GetMapping("/recommended")
    private List<Product>getRecommendedProducts(){
        return productService.getRecommendedProducts();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Result>editProduct(@PathVariable Integer id,@RequestBody ProductLoader productLoader){
        Result result = productService.editProduct(id, productLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result>deleteProduct(@PathVariable Integer id){
        Result result = productService.deleteProduct(id);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    @PostMapping(value = "/{productId}/addPhoto")
    public ResponseEntity<Result>addProductPhoto(@PathVariable Integer productId, MultipartHttpServletRequest request) throws ServletException, IOException {
        Result result = productService.addProductPhoto(productId, request);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    
}
