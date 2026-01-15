package com.example.demoauth.controller;

import com.example.demoauth.shared.web.ApiResponse;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/users/internal")
public class InterNalController {

    @GetMapping("/getUserByID")
    public ApiResponse<?> getUserByID(
            @RequestParam UUID id,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        return null;
    }
}