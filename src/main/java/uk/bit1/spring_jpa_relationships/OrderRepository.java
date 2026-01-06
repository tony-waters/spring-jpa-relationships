package uk.bit1.spring_jpa_relationships;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Order findById(long id);

}
