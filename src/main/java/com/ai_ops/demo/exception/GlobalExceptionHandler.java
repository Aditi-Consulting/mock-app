package com.ai_ops.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Primary path for application-level failures intended for the service LLM.
     * Emits a minimal, structured payload: error, code, message, location,
     * context, timestamp.
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String, Object>> handleServiceException(ServiceException ex) {
        Map<String, Object> body = new LinkedHashMap<>();

        Throwable cause = ex.getCause();
        Throwable source = (cause != null) ? cause : ex;
        String errorType = source.getClass().getSimpleName();

        body.put("error", errorType);
        body.put("code", ex.getCode());
        body.put("message", ex.getMessage());
        body.put("location", getLocationFromStack(source));
        body.put("context", ex.getContext());
        body.put("timestamp", Instant.now().toString());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private String getLocationFromStack(Throwable ex) {
        StackTraceElement[] stackTrace = ex.getStackTrace();
        if (stackTrace.length == 0) {
            return "unknown";
        }
        StackTraceElement el = stackTrace[0];
        return el.getClassName() + "." + el.getMethodName() + ":" + el.getLineNumber();
    }
}

