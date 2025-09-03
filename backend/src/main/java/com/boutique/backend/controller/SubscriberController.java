package com.boutique.backend.controller;

import com.boutique.backend.SubscriberDTO.SubscriberDTO;
import com.boutique.backend.model.Subscriber;
import com.boutique.backend.repository.SubscriberRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/subscribe")
public class SubscriberController {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @PostMapping
    public ResponseEntity<?> subscribe(@Valid @RequestBody SubscriberDTO subscriberDTO) {
        if (subscriberRepository.existsByEmail(subscriberDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email already subscribed");
        }

        Subscriber subscriber = new Subscriber(subscriberDTO.getEmail());
        subscriberRepository.save(subscriber);
        return ResponseEntity.ok("Subscription successful!");
    }
}
