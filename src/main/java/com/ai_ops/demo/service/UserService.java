package com.ai_ops.demo.service;

import com.ai_ops.demo.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * Fetches all users - returns null to simulate a code bug.
     */
    private List<Map<String, Object>> fetchUsers() {
        // BUG: Returns null instead of empty list
        return null;
    }

    /**
     * Gets all users - NPE occurs naturally because fetchUsers() returns null
     * and we don't handle it.
     */
    public List<Map<String, Object>> getAllUsers() {
        logger.info("Fetching all users");

        List<Map<String, Object>> users = fetchUsers();

        try {
            // BUG: No null check - NPE occurs naturally here
            logger.info("Found {} users", users.size());
            return users;
        } catch (NullPointerException e) {
            throw new ServiceException(
                "Null pointer exception occurred while fetching users",
                "NULL_POINTER_EXCEPTION",
                Map.of(
                    "endpoint", "/api/users",
                    "httpMethod", "GET",
                    "statusCode", 500,
                    "service", "UserService",
                    "method", "getAllUsers"
                ),
                e
            );
        }
    }
}

