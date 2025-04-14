package dev.nathan.refrigerator.exceptions;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
// Remove AiRuntimeException import if not directly used
// import org.springframework.ai.exception.AiRuntimeException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.warn("Resource not found: {}", ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    // Handler for general Runtime exceptions, potentially including AI errors
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex) {
        // Check if the cause indicates an AI quota issue specifically
        // This is a basic check, more robust checks might be needed depending on exception types
        if (ex.getMessage() != null && ex.getMessage().contains("insufficient_quota")) {
            log.error("AI Service Quota Exceeded: {}", ex.getMessage()); // Log full message
            // Return 429 Too Many Requests or 503 Service Unavailable
            ApiError apiError = new ApiError(HttpStatus.TOO_MANY_REQUESTS, "AI service quota exceeded. Please check OpenAI account status.");
            return new ResponseEntity<>(apiError, HttpStatus.TOO_MANY_REQUESTS);
        } else if (ex.getMessage() != null && ex.getMessage().contains("429")) {
            log.error("AI Service Rate Limit / Error (429): {}", ex.getMessage());
            ApiError apiError = new ApiError(HttpStatus.TOO_MANY_REQUESTS, "AI service unavailable (rate limit or other issue). Please try again later.");
            return new ResponseEntity<>(apiError, HttpStatus.TOO_MANY_REQUESTS);
        }
        else {
            // Handle other generic runtime exceptions as Internal Server Error
            log.error("An unexpected runtime error occurred: {}", ex.getMessage(), ex);
            ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "An internal server error occurred");
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Fallback for any other Exception type (less likely if RuntimeException is handled)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        // Avoid handling RuntimeException again if it falls through
        if (ex instanceof RuntimeException) {
            // Should have been caught by handleRuntimeException, but as a safeguard:
            log.error("Caught RuntimeException in generic handler: {}", ex.getMessage(), ex);
            ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected internal server error occurred");
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.error("An unexpected non-runtime error occurred: {}", ex.getMessage(), ex);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "An internal server error occurred");
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // ApiError Helper Class (Keep as before)
    @Getter
    public static class ApiError {
        private final int status;
        private final String error;
        private final String message;

        public ApiError(HttpStatus status, String message) {
            this.status = status.value();
            this.error = status.getReasonPhrase();
            this.message = message;
        }
    }
}