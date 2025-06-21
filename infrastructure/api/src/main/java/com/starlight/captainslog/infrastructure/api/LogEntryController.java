package com.starlight.captainslog.infrastructure.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/log-entries")
public class LogEntryController {

    @PostMapping
    public ResponseEntity<Map<String,String>> recordLogEntry(@RequestBody Map<String,String> payload) {
        return ResponseEntity
                .status(201)
                .body(payload);
    }

}
