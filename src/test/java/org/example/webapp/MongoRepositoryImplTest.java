package org.example.webapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MongoRepositoryImplTest {

    @Autowired
    private MongoRepositoryImpl mongoRepositoryImpl;

    @Autowired
    private MyMongoRepository myMongoRepository;

    @Test
    public void testFindAll() {
        assertThat(mongoRepositoryImpl.findAll()).isNotNull();
    }

    @Test
    public void testFindById() {
        Products product = new Products();
        product.setId("1");
        product.setName("Test Product");
        myMongoRepository.save(product);

        Optional<Products> foundProduct = mongoRepositoryImpl.findById("1");
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo("Test Product");
    }

    @Test
    public void testSave() {
        Products product = new Products();
        product.setId("1");
        product.setName("Test Product");

        Products savedProduct = mongoRepositoryImpl.save(product);
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo("Test Product");
    }

    @Test
    public void testDeleteById() {
        Products product = new Products();
        product.setId("1");
        product.setName("Test Product");
        myMongoRepository.save(product);

        mongoRepositoryImpl.deleteById("1");
        Optional<Products> deletedProduct = myMongoRepository.findById("1");
        assertThat(deletedProduct).isNotPresent();
    }
}