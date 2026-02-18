package com.ai_ops.demo.controller;

import com.ai_ops.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET /api/users
     *
     * Fetches all users - triggers NullPointerException because
     * the internal method returns null and we don't handle it.
     */
    @GetMapping("/users")
    public ResponseEntity<List<Map<String, Object>>> getUsers() {
        logger.info("Fetching all users");
        List<Map<String, Object>> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}

