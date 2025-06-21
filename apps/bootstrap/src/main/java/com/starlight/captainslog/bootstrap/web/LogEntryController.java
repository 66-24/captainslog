package com.starlight.captainslog.bootstrap.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
