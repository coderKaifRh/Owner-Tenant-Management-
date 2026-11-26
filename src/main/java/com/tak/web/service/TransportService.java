package com.tak.web.service;

import org.springframework.stereotype.Service;

@Service
public class TransportService {

    public double calculateCost(String size, int workers) {
        if (workers < 0) {
            throw new IllegalArgumentException("Worker count cannot be negative!");
        }

        double base;
        if ("Large".equalsIgnoreCase(size)) {
            base = 5000;
        } else if ("Medium".equalsIgnoreCase(size)) {
            base = 3500;
        } else {
            base = 2000;
        }

        return base + (workers * 800);
    }
}
