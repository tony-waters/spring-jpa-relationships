package uk.bit1.spring_jpa_relationships;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import uk.bit1.spring_jpa_relationships.entity.Customer;
import uk.bit1.spring_jpa_relationships.entity.Order;
import uk.bit1.spring_jpa_relationships.repository.CustomerRepository;
import uk.bit1.spring_jpa_relationships.repository.OrderRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    private Customer testCustomer;
    private Order testOrder;

    @BeforeEach
    public void setUp() {
        testOrder = new Order("Test Order 1");
        testCustomer = new Customer("Bloggs", "Jo");
        testCustomer.addOrder(testOrder);
        customerRepository.save(testCustomer);
    }

    @AfterEach
    public void tearDown() {
        customerRepository.delete(testCustomer);

    }

    @Test
    void order_can_be_found_by_id() {
        Order order = orderRepository.findById(testOrder.getId()).orElse(null);
        assertNotNull(order);
        assertEquals(testOrder.getDescription(), order.getDescription());
    }

    @Test
    void customer_can_be_found_through_order() {
        Order order = orderRepository.findById(testOrder.getId()).orElse(null);
        assertNotNull(order);

        Customer customer = order.getCustomer();
        assertEquals(testCustomer.getId(), customer.getId());
        assertEquals(testCustomer.getLastName(), customer.getLastName());
        assertEquals(testCustomer.getFirstName(), customer.getFirstName());
    }

    @Test
    void order_details_can_be_updated() {
        testOrder.setDescription("Updated description");
        customerRepository.save(testCustomer);

        Customer customer = customerRepository.findById(testCustomer.getId()).orElse(null);
        assertNotNull(customer);
        assertEquals("Updated description", customer.getOrders().get(0).getDescription());
    }

}
