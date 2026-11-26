package com.tak.web.controller;

import com.tak.web.model.Flat;
import com.tak.web.model.User;
import com.tak.web.service.FlatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    private final FlatService flatService;
    private static final String UPLOAD_DIR = "E:/tak-web/uploads/";

    @Autowired
    public OwnerController(FlatService flatService) {
        this.flatService = flatService;
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null || !user.isOwner()) {
            return "redirect:/login";
        }

        List<Flat> myFlats = flatService.getFlatsByOwner(user.getUserId());
        model.addAttribute("user", user);
        model.addAttribute("flats", myFlats);
        return "owner-dashboard";
    }

    @PostMapping("/add-flat")
    public String addFlat(@RequestParam String location,
                          @RequestParam Double rent,
                          @RequestParam Integer size,
                          @RequestParam Integer bedroom,
                          @RequestParam Integer washroom,
                          @RequestParam(value = "photos", required = false) MultipartFile[] photos,
                          HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null || !user.isOwner()) {
            return "redirect:/login";
        }

        Flat flat = new Flat(user.getUserId(), location, rent, size, bedroom, washroom);

        // Handle uploaded images
        if (photos != null && photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()) {
                    try {
                        String originalFilename = photo.getOriginalFilename();
                        String extension = "";
                        if (originalFilename != null && originalFilename.contains(".")) {
                            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                        }
                        String fileName = "flat_" + UUID.randomUUID() + extension;
                        Path filePath = Paths.get(UPLOAD_DIR, fileName);
                        Files.write(filePath, photo.getBytes());

                        // URL path to access from browser
                        flat.addImage("/uploads/" + fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        flatService.saveFlat(flat);
        return "redirect:/owner/dashboard?success=true";
    }
}
