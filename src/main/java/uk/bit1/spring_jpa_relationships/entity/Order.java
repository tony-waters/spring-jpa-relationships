package uk.bit1.spring_jpa_relationships.entity;

import jakarta.persistence.*;

@Entity(name = "CustomerOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    protected Order() {
    }

    public Order(String description) {
        this.description = description;

    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return String.format(
                "Order[id=%d, description='%s']",
                id, description);
    }
}
