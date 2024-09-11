package org.lfx.controller;

import org.lfx.model.Customer;
import org.lfx.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customers"; // Refers to customers.html in the templates folder
    }

    @GetMapping("/add")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "add-customer"; // Refers to add-customer.html in the templates folder
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String showEditCustomerForm(@PathVariable("id") Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        model.addAttribute("customer", customer);
        return "edit-customer"; // Refers to edit-customer.html in the templates folder
    }

   
    
    @PostMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") Long id, @ModelAttribute Customer customer) {
        // Ensure the customer with the provided ID exists
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        
        // Update the customer's details
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        
        // Save the updated customer back to the repository
        customerRepository.save(existingCustomer);
        
        // Redirect to the list of customers after saving the changes
        return "redirect:/customers";
    }


    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        customerRepository.delete(customer);
        return "redirect:/customers";
    }
}
