package com.yorusito.customer.domain.service;

import com.yorusito.authentication.domain.model.User;
import com.yorusito.authentication.domain.service.UserService;
import com.yorusito.customer.domain.model.Customer;
import com.yorusito.customer.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;

    public CustomerService(CustomerRepository customerRepository, UserService userService) {
        this.customerRepository = customerRepository;
        this.userService = userService;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.setFirstName(customerDetails.getFirstName());
            customer.setLastName(customerDetails.getLastName());
            customer.setEmail(customerDetails.getEmail());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());

            // Actualizar el rol del usuario si es necesario
            User user = customer.getUser();
            if (customerDetails.getUser() != null && customerDetails.getUser().getRole() != null) {
                user.setRole(customerDetails.getUser().getRole()); // Actualizamos el rol
                userService.saveUser(user); // Guardamos los cambios del usuario
            }

            return customerRepository.save(customer); // Guardamos los cambios del cliente
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
