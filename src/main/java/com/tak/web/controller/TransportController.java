package com.tak.web.controller;

import com.tak.web.model.User;
import com.tak.web.service.TransportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transport")
public class TransportController {

    private final TransportService transportService;

    @Autowired
    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @GetMapping
    public String showTransportPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        model.addAttribute("user", user);
        return "transport";
    }

    @PostMapping("/calculate")
    public String calculateFare(@RequestParam String size,
                                @RequestParam Integer workers,
                                HttpSession session,
                                Model model) {
        User user = (User) session.getAttribute("currentUser");
        model.addAttribute("user", user);

        try {
            double totalCost = transportService.calculateCost(size, workers);
            model.addAttribute("selectedSize", size);
            model.addAttribute("workerCount", workers);
            model.addAttribute("totalCost", totalCost);
            model.addAttribute("calculated", true);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "transport";
    }
}
