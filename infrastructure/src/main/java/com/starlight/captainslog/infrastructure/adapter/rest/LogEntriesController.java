package com.starlight.captainslog.infrastructure.adapter.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/log-entries")
public class LogEntriesController {

    @PostMapping
    public ResponseEntity<Map<String,String>> recordLogEntry(@RequestBody Map<String,String> payload) {
        return ResponseEntity
                .status(201)
                .body(payload);
    }

}
