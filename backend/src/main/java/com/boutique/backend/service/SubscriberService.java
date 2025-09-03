package com.boutique.backend.service;


import com.boutique.backend.model.Subscriber;
import com.boutique.backend.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {
    @Autowired
    private SubscriberRepository repository;

    public Subscriber saveSubscriber(Subscriber subscriber) {
        return repository.save(subscriber);
    }
}