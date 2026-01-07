package uk.bit1.spring_jpa_relationships.repository;

import org.springframework.data.repository.CrudRepository;
import uk.bit1.spring_jpa_relationships.entity.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findById(long id);

    List<Customer> findByLastName(String lastName);

}
