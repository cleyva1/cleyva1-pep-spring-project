package com.example.controller;
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
    AccountService accountService;
    MessageService messageService;
    
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody Account account) {
        if (account.getUsername() != "" && account.getPassword().length() >= 4) {
            try {
                Account newAccount = accountService.addAccount(account);
                return ResponseEntity.status(200).body(newAccount);
            } catch (DuplicateUsernameException e) {
                return ResponseEntity.status(409).body("Duplicate Username");
            }
        }
        else {
            return ResponseEntity.status(400).body("User error");
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Account account) {
        return null;
    }

    @PostMapping("/messages")
    public ResponseEntity postMessage(@RequestBody Message message) {
        return null;
    }

    @GetMapping("/messages")
    public ResponseEntity getAllMessages() {
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity getMessageById(@PathVariable Integer messageId) {
        return null;
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity deleteMessage(@PathVariable Integer messageId) {
        return null;
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity updateMessage(@PathVariable Integer messageId) {
        return null;
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity getAllMessagesByUser(@PathVariable Integer accountId) {
        return null;
    }

}
