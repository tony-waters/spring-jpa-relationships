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

    private Order testOrder;

    private Product testProduct;

    @BeforeEach
    public void setUp() {
        testProduct = new Product("Sombrero Hat", "Traditional south american hat");
        productRepository.save(testProduct);

        testOrder = new Order("Test order");
        testOrder.addProduct(testProduct);
    }

    @AfterEach
    public void tearDown() {
        productRepository.delete(testProduct);
        orderRepository.delete(testOrder);
    }

    @Test
    void product_can_be_found_by_id() {
        Product product = productRepository.findById(testProduct.getId()).orElse(null);
        assertNotNull(product);
        assertEquals(testProduct.getName(), product.getName());
        assertEquals(testProduct.getDescription(), product.getDescription());
    }

    @Test
    void product_can_get_its_orders_and_order_can_get_its_products() {
        assertNotNull(testProduct.getOrders());
        assertNotNull(testOrder.getProducts());
        assertEquals(1, testOrder.getProducts().size());
        assertEquals(1, testProduct.getOrders().size());
    }
}
