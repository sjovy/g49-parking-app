package se.lexicon.data;

import se.lexicon.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    Customer store(Customer customer);

    Optional<Customer> find(int id);

    boolean remove(int id);

    List<Customer> findAll();
}
