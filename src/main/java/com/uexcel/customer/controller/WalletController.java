package com.uexcel.customer.controller;

import com.uexcel.customer.entity.Wallet;
import com.uexcel.customer.entity.WalletTransaction;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)

public class WalletController {
    @GetMapping("/wallet")
    public ResponseEntity<Wallet> fetchWallet(@RequestParam long walletId) {
        return null;
    }

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> fund(WalletTransaction wt) {
        return null;
    }

    @PutMapping("/wallet")
    public ResponseEntity<Wallet> update(WalletTransaction wt) {
        return null;
    }
}
