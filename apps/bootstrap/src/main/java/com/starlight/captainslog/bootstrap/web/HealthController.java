package com.starlight.captainslog.bootstrap.web;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String healthCheck() {
        return "Ahoy! All systems green. 🌊🟢";
    }

}
