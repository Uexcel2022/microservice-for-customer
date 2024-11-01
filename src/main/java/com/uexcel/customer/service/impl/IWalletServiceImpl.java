package com.uexcel.customer.service.impl;

import com.uexcel.customer.constants.ICustomerConstants;
import com.uexcel.customer.entity.Wallet;
import com.uexcel.customer.entity.WalletTransaction;
import com.uexcel.customer.exception.BadRequestException;
import com.uexcel.customer.exception.ResourceNotFoundException;
import com.uexcel.customer.repository.WalletRepository;
import com.uexcel.customer.repository.WalletTransactionRepsitory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IWalletServiceImpl implements IwalletService{
    private final WalletRepository walletRepository;
    private final WalletTransactionRepsitory wTransactionRepository;
    /**
     * @param walletId - the customer wallet id
     * @return Wallet details
     */
    @Override
    public Wallet fetchWallet(long walletId) {
        return getWallet(walletId);
    }

    /**
     * @param wt - request body - wallet transaction
     * @return boolean value indicating update is successful or not
     */
    @Override
    public boolean updateWallet(WalletTransaction wt) {
        Wallet wallet = getWallet(wt.getWalletId());
        double newBal = wt.getAmount()+wallet.getBalance();
          wallet.setBalance(newBal);
          walletRepository.save(wallet);
          wTransactionRepository.save(wt);
        return true;
    }

    /**
     * @param wt - request body - wallet transaction
     * @return boolean value indicating wallet funding is successful or not
     */
    @Override
    public boolean fundWallet(WalletTransaction wt) {
        getWallet(wt.getWalletId());
        wTransactionRepository.save(wt);
        return true;
    }

    private Wallet getWallet(long walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(()-> new ResourceNotFoundException(
                                "Wallet","walletId",Long.toString(walletId)
                        )
                );
        if (ICustomerConstants.CUSTOMER_DEACTIVATED.equals(wallet.getStatus())){
            throw new BadRequestException("Customer is deactivated");
        }
        return wallet;
    }
}
