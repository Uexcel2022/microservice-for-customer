package com.uexcel.customer.service.impl;

import com.uexcel.customer.entity.Wallet;
import com.uexcel.customer.entity.WalletTransaction;

public interface IwalletService {

    /**
     * @param walletId - the customer wallet id
     * @return Wallet details
     */
    Wallet fetchWallet(long walletId);

    /**
     * @param wt  - request body - wallet transaction
     * @return boolean value indicating update is successful or not
     */
    boolean updateWallet(WalletTransaction wt);

    /**
     * @param wt  - request body - wallet transaction
     * @return boolean value indicating wallet funding is successful or not
     */
    boolean fundWallet(WalletTransaction wt);


}
