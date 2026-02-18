package com.ai_ops.demo.exception;

import java.util.Map;

/**
 * Application-level exception carrying a stable error code and rich context
 * for downstream AI/alerting services. Intended for controlled failures
 * where we want to expose structured information instead of a generic error.
 */
public class ServiceException extends RuntimeException {

    private final String code;
    private final Map<String, Object> context;

    public ServiceException(String message, String code, Map<String, Object> context, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.context = context;
    }

    public String getCode() {
        return code;
    }

    public Map<String, Object> getContext() {
        return context;
    }
}

