package org.lfx.config;

import org.lfx.model.Customer;
import org.lfx.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        customerRepository.save(new Customer("John Doe", "john.doe@example.com"));
        customerRepository.save(new Customer("Jane Smith", "jane.smith@example.com"));
        customerRepository.save(new Customer("Mike Johnson", "mike.johnson@example.com"));
    }
}
