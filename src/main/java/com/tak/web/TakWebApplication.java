package com.tak.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TakWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TakWebApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        String url = "http://localhost:8081/login";
        System.out.println("\n============================================================");
        System.out.println("   🚀 TAK Limited Web Application is ready!");
        System.out.println("   👉 URL: " + url);
        System.out.println("============================================================\n");

        try {
            if (System.getProperty("os.name", "").toLowerCase().contains("win")) {
                new ProcessBuilder("rundll32", "url.dll,FileProtocolHandler", url).start();
            }
        } catch (Exception ignored) {
            // If auto-open fails, URL is already printed in console above
        }
    }
}
