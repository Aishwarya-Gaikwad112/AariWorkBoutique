package com.boutique.backend.controller;

import com.boutique.backend.model.ContactMessage;
import com.boutique.backend.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*") // Enable CORS if frontend is separate
public class ContactController {

    @Autowired
    private ContactMessageRepository repo;

    @PostMapping
    public ResponseEntity<?> receiveMessage(@RequestBody ContactMessage message) {
        repo.save(message);
        return ResponseEntity.ok("Message sent successfully!");
    }
}
