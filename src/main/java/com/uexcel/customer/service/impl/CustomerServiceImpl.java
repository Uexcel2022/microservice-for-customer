package com.uexcel.customer.service.impl;

import com.uexcel.customer.dto.CustomerDto;

import com.uexcel.customer.entity.Customer;
import com.uexcel.customer.entity.Wallet;
import com.uexcel.customer.exception.BadRequestException;
import com.uexcel.customer.exception.ResourceNotFoundException;
import com.uexcel.customer.mapper.ICustomerMapper;
import com.uexcel.customer.repository.CustomerRepository;
import com.uexcel.customer.repository.WalletRepository;
import com.uexcel.customer.service.ICustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final ICustomerMapper iCustomerMapper;
    private  final CustomerRepository customerRepository;
    private  final WalletRepository welledRepository;

    /**
     * @param customerDto - holds customer details for registration
     */
    @Override
    @Transactional
    public void createCustomer(CustomerDto customerDto) {
        Customer customer =
                customerRepository.save(iCustomerMapper
                        .mapToNewCustomer(customerDto));
        Wallet  wallet = iCustomerMapper.createWallet(customer.getId());
        welledRepository.save(wallet);
    }

    /**
     * @param emailOrPhone - customer email or phone number
     * @return - return customer info with customerDto objet
     */
    @Override
    public CustomerDto getCustomer(String emailOrPhone) {
        Customer customer = customerExists(emailOrPhone);
        Wallet wallet = walletExists(customer.getId());
      return   iCustomerMapper.mapToCustomerDto(customer,wallet);
    }

    /**
     * @param customerDto - holding updated information
     * @return - Returns boolean value indicating update successful or not
     */
    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        Customer customer = customerExists(customerDto.getEmailAddress());
       customerRepository.save(
               iCustomerMapper.mapToUpdateCustomer(customerDto,customer));
        return true;
    }

    /**
     * @param emailOrPhone - customer email or phone
     * @return - return boolean value indicating delete is successful or not
     */
    public boolean deleteCustomer(String emailOrPhone) {
        Customer customer = customerExists(emailOrPhone);
        Wallet wallet = walletExists(customer.getId());
        customerRepository.delete(customer);
        welledRepository.delete(wallet);
        return true;
    }



    private Customer customerExists(String emailOrPhone) {
        return customerRepository
                .findByEmailAddressOrPhoneNumber(emailOrPhone,emailOrPhone)
                .orElseThrow(()->{
                    if(emailOrPhone!= null && !emailOrPhone.isEmpty()) {
                        if (emailOrPhone.contains("@")) {
                            return new ResourceNotFoundException("Customer", "emailAddress", emailOrPhone);
                        } else {
                            return new ResourceNotFoundException("Customer", "PhoneNumber", emailOrPhone);
                        }
                    } else {
                        return new BadRequestException("The inputted value is Null or Empty");
                    }
                });
    }


    private Wallet walletExists(String customerId) {
       return welledRepository.findByCustomerId(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Wallet","CustomerId",customerId));
    }



}
