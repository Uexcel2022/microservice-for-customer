package com.uexcel.customer.mapper;

import com.uexcel.customer.dto.CustomerDto;
import com.uexcel.customer.entity.Customer;
import com.uexcel.customer.entity.Wallet;

import java.util.Random;

public interface ICustomerMapper {

    default Customer mapToNewCustomer(CustomerDto customerDto ) {
        Customer customer = new Customer();
        customer.setEmail(customerDto.getEmail());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPassword(customerDto.getPassword());
        customer.setPhone(customerDto.getPhone());
        return customer;
    }

    default CustomerDto mapToCustomerDto(Customer customer, Wallet wallet) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail(customer.getEmail());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setPhone(customer.getPhone());
        customerDto.setWallet(wallet);
        return customerDto;


    }

    default Customer mapToUpdateCustomer(CustomerDto customerDto, Customer customer ) {
        customer.setEmail(customerDto.getEmail());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhone(customerDto.getPhone());
        return customer;
    }

    default Wallet createWallet(String customerId) {
        Long walletId = 1000000000000L + new  Random().nextInt(900000000);
        Wallet wallet = new Wallet();
        wallet.setWallId(walletId);
        wallet.setCustomerId(customerId);
        wallet.setBalance(1500.00);
        return wallet;
    }

}
