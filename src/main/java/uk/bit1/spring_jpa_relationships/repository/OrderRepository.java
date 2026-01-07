package uk.bit1.spring_jpa_relationships.repository;

import org.springframework.data.repository.CrudRepository;
import uk.bit1.spring_jpa_relationships.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Order findById(long id);

}
