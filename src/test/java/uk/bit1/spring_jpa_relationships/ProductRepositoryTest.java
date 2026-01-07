package uk.bit1.spring_jpa_relationships;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import uk.bit1.spring_jpa_relationships.entity.Order;
import uk.bit1.spring_jpa_relationships.entity.Product;
import uk.bit1.spring_jpa_relationships.repository.OrderRepository;
import uk.bit1.spring_jpa_relationships.repository.ProductRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    void product_can_be_found_by_id() {
        Product product = productRepository.findById(testProduct.getId()).orElse(null);
        assertNotNull(product);
        assertEquals(testProduct.getName(), product.getName());
        assertEquals(testProduct.getDescription(), product.getDescription());
    }

    @Test
    void product_can_get_its_orders() {
        Order order = new Order("New order");
        List<Order> orders = testProduct.getOrders();
        assertNotNull(orders);
        assertEquals(0, orders.size());
    }

}
