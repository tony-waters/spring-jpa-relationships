package uk.bit1.spring_jpa_relationships;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import uk.bit1.spring_jpa_relationships.entity.Product;
import uk.bit1.spring_jpa_relationships.repository.OrderRepository;
import uk.bit1.spring_jpa_relationships.repository.ProductRepository;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    private Product testProduct;

    @BeforeEach
    public void setUp() {
        testProduct = new Product("Sombrero Hat", "Traditional south american hat");
        productRepository.save(testProduct);
    }

    @AfterEach
    public void tearDown() {
        productRepository.delete(testProduct);
    }

    @Test
    void cen_get_product_by_id() {

    }

}
