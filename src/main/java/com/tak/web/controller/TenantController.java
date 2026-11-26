package com.tak.web.controller;

import com.tak.web.model.Flat;
import com.tak.web.model.User;
import com.tak.web.service.FlatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tenant")
public class TenantController {

    private final FlatService flatService;

    @Autowired
    public TenantController(FlatService flatService) {
        this.flatService = flatService;
    }

    @GetMapping("/search")
    public String showSearchPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "tenant-search";
    }

    @GetMapping("/results")
    public String showResults(@RequestParam(required = false) String location,
                              @RequestParam(required = false) Double maxRent,
                              @RequestParam(required = false) Integer minSize,
                              HttpSession session,
                              Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }

        List<Flat> results = flatService.searchFlats(location, maxRent, minSize);
        model.addAttribute("user", user);
        model.addAttribute("flats", results);
        model.addAttribute("searchLocation", location);
        model.addAttribute("searchMaxRent", maxRent);
        model.addAttribute("searchMinSize", minSize);

        // Required advance payment calculation
        if (maxRent != null) {
            double advance = user.calculateAdvance(maxRent);
            model.addAttribute("estimatedAdvance", advance);
        }

        return "results";
    }
}
