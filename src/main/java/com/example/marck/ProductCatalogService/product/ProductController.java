package com.example.marck.ProductCatalogService.product;

import com.example.marck.ProductCatalogService.AuthService;
import com.example.marck.ProductCatalogService.product.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {
    // TODO: Add error handling

    private final ProductService productService;
    private final AuthService authService;

    @Autowired
    public ProductController(ProductService productService, AuthService authService) {
        this.productService = productService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        if (!authService.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(this.productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping(path = "/{productName}")
    public ResponseEntity<Product> getProductByName(@PathVariable("productName") String productName) {
        if (!authService.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(this.productService.getProductByName(productName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (!authService.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(path = "/{productName}/stock")
    public ResponseEntity<Integer> getProductStockByName(@PathVariable("productName") String productName) {
        if (!authService.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(this.productService.getProductStockByName(productName), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{productName}")
    public void deleteProductByName(@PathVariable("productName") String productName) {
        if (!authService.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!productService.productHasUnfinishedOrders()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.productService.deleteProductByName(productName);
    }
}

