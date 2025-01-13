package org.example.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    private MyMongoRepository myMongoRepository;

    @GetMapping
    public List<Products> getAllProducts() {
        return myMongoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable String id) {
        Optional<Products> product = myMongoRepository.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Products createProduct(@RequestBody Products product) {
        return myMongoRepository.save(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable String id, @RequestBody Products productDetails) {
        Optional<Products> product = myMongoRepository.findById(id);
        if (product.isPresent()) {
            Products updatedProduct = product.get();
            updatedProduct.setName(productDetails.getName());
            return ResponseEntity.ok(myMongoRepository.save(updatedProduct));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        if (myMongoRepository.existsById(id)) {
            myMongoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
