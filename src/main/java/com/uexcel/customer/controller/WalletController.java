package com.uexcel.customer.controller;

import com.uexcel.customer.dto.WalletDto;
import com.uexcel.customer.entity.Wallet;
import com.uexcel.customer.entity.WalletTransaction;
import com.uexcel.customer.exception.ExceptionFail;
import com.uexcel.customer.service.IwalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("http://localhost:8080")
public class WalletController {
    private final IwalletService iwalletService;
    @GetMapping("/wallet")
    public ResponseEntity<WalletDto> fetchWallet(@RequestParam long walletId) {
        return ResponseEntity.ok(iwalletService.fetchWallet(walletId));
    }

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> fund(WalletTransaction wt) {
        return null;
    }

    @PutMapping("/wallet")
    public ResponseEntity<Boolean> update(@RequestBody WalletTransaction wt) {
      boolean success =  iwalletService.updateWallet(wt);
        if(success){
            return ResponseEntity.ok(true);
        }
        throw  new ExceptionFail("Fail");
    }
}
