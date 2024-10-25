package com.uexcel.customer.service.impl;

import com.uexcel.customer.dto.CustomerDto;

import com.uexcel.customer.entity.BaseEntity;
import com.uexcel.customer.entity.Customer;
import com.uexcel.customer.entity.Wallet;
import com.uexcel.customer.mapper.ICustomerMapper;
import com.uexcel.customer.repository.CustomerRepository;
import com.uexcel.customer.repository.WalletRepository;
import com.uexcel.customer.service.ICustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final ICustomerMapper iCustormerMapper;
    private  final CustomerRepository customerRepository;
    private  final WalletRepository welletRepository;

    /**
     * @param customerDto - holds customer details for registration
     */
    @Override
    @Transactional
    public void createCustomer(CustomerDto customerDto) {
        Customer customer =
                customerRepository.save(iCustormerMapper
                        .mapToNewCustomer(customerDto));
        Wallet  wallet = iCustormerMapper.createWallet(customer.getId());
        welletRepository.save(wallet);
    }

    /**
     * @param emailOrPhone - customer email or phone number
     * @return - return customer info with customerDto objet
     */
    @Override
    public CustomerDto getCustomer(String emailOrPhone) {
        List<BaseEntity> baseEntity = getCustomerAndWallet(emailOrPhone);
        Customer customer = (Customer) baseEntity.get(0);
        Wallet wallet = (Wallet) baseEntity.get(1);
      return   iCustormerMapper.mapToCustomerDto(customer,wallet);
    }

    /**
     * @param customerDto - holding updated information
     * @return - Returns boolean value indicating update successful or not
     */
    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository
                .findByEmailOrPhone(customerDto.getEmail(),customerDto.getEmail())
                .orElseThrow(()->new RuntimeException("Not found"));
       customerRepository.save(
               iCustormerMapper.mapToUpdateCustomer(customerDto,customer));
        return true;
    }

    /**
     * @param emailOrPhone - customer email or phone
     * @return - return boolean value indicating delete is successful or not
     */
    public boolean deleteCustomer(String emailOrPhone) {
        List<BaseEntity> baseEntity = getCustomerAndWallet(emailOrPhone);
        Customer customer = (Customer) baseEntity.get(0);
        Wallet wallet = (Wallet) baseEntity.get(1);
        customerRepository.delete(customer);
        welletRepository.delete(wallet);
        return true;
    }




    private List<BaseEntity> getCustomerAndWallet(String emailOrPhone) {
        Customer customer = customerRepository
                .findByEmailOrPhone(emailOrPhone,emailOrPhone)
                .orElseThrow(()->new RuntimeException("Not found"));
        Wallet wallet =  welletRepository.findByCustomerId(customer.getId())
                .orElseThrow(()->new RuntimeException("Not found"));
        return List.of(customer,wallet);

    }

}
