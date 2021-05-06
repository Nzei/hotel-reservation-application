package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    public Collection<Customer> customerList = new HashSet<>();

    private static CustomerService customerService = null;

    private CustomerService(){

    }

    public static CustomerService getSingleInstance() {
        if(customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName,lastName,email);
        customerList.add(customer);
    }

    public Customer getCustomer (String customerEmail){
        if(customerEmail.isEmpty() || customerEmail.isEmpty() || customerEmail.equals("")) {
            throw new NullPointerException("Parameter passed is empty, please enter a parameter");
        }
        for(Customer customer:this.customerList) {
            if(customer.getEmail().equals(customerEmail)){
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
       if(!this.customerList.isEmpty()){
           return this.customerList;
       }
       return customerList;
    }



}
