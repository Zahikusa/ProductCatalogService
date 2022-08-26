package com.example.marck.ProductCatalogService.product;

import com.example.marck.ProductCatalogService.product.models.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    // TODO: Debug why DB initialization is not working

    private final JdbcTemplate jtm;

    public ProductService(JdbcTemplate jtm) {
        this.jtm = jtm;
    }

    public List<Product> getProducts() {
        String sql = "SELECT * FROM products";
        return jtm.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public Product getProductByName(String productName) {
        String sql = "SELECT * FROM products";
        return jtm.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), productName);
    }

    public Product createProduct(Product product) {
        String sql = "INSERT INTO products (name, type, description, stock) VALUES (?,?,?,?)";
        jtm.update(sql, product.getName(), product.getType(), product.getDescription(), product.getStock());
        return product;
    }

    public void deleteProductByName(String productName) {
        String sql = "DELETE FROM products WHERE name =" + productName;
        jtm.execute(sql);
    }

    public Integer getProductStockByName(String productName) {
        // TODO Implement this method
        return 2;
    }

    public void updateProductStockByName(String productName, Integer stockIncrease) {
        if (stockIncrease < 1) return; // TODO: Improve error handling here, throw a specific error
        String sql = "UPDATE products SET stock = ? WHERE name = ?";
        jtm.update(sql, stockIncrease, productName);
    }

    public boolean productHasRunningOrders(String productName) {
        // TODO implement this method by accessing API of OrderingService
    }
}
