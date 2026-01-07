package uk.bit1.spring_jpa_relationships.repository;

import org.springframework.data.repository.CrudRepository;
import uk.bit1.spring_jpa_relationships.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
