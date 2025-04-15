package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.DuplicateUsernameException;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Account account) {
        try {
            Account newAccount = accountService.addAccount(account);
            return ResponseEntity.status(200).body(newAccount);
        } catch (DuplicateUsernameException e) {
            return ResponseEntity.status(409).body("Duplicate Username");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Invalid Username or Password");
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        boolean loginSuccess = accountService.login(account.getUsername(), account.getPassword());
        if (loginSuccess) {
            
        }
        return null;
    }

    @PostMapping("/messages")
    public ResponseEntity<?> postMessage(@RequestBody Message message) {
        return null;
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getAllMessages() {
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<?> getMessageById(@PathVariable Integer messageId) {
        return null;
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable Integer messageId) {
        return null;
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable Integer messageId) {
        return null;
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<?> getAllMessagesByUser(@PathVariable Integer accountId) {
        return null;
    }

}
