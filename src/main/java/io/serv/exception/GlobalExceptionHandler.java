package io.serv.exception;

import io.serv.api.common.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.List;

/** Centralised exception handler for REST API errors. 
 *  
 * Converts application, validation, authentication, authorisation, 
 * upload, and unexpected exceptions into a consistent format. 
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

/** Handles application-specific API exceptions. 
 * 
 * @param e the API exception containing the HTTP status, error code, and message 
 * @return a response containing the corresponding error
 */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException e) {
        if (e.status().is5xxServerError()) {
            log.error("Unhandled API exception", e);
        }
        return ResponseEntity.status(e.status())
                .body(ErrorResponse.of(e.status().value(), e.statusCode(), e.getMessage()));
    }

/** Handles validation errors produced when a request body fails
 * Jakarta Bean Validation.
 * 
 * @param e the validation exception containing field-level errors
 * @return a bad-request response containing the validation errors 
*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e) {
        List<ErrorResponse.FieldError> fieldErrors = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> new ErrorResponse.FieldError(fe.getField(), messageOf(fe)))
                .toList();
        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(400, "VALIDATION_ERROR", "Request validation failed", fieldErrors));
    }

/** Handles validation errors produced by method or parameter
 * constraint validation. 
 * 
 * @param e the constraint violation exception
 * @return a bad-request response containing the validation message 
*/
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException e) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(400, "VALIDATION_ERROR", e.getMessage()));
    }

/** Handles failed authentication caused by invalid credentials.
 * 
 * @param e the authentication exception
 * @return an unauthorized response 
*/
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.of(401, "UNAUTHORIZED", "Invalid credentials"));
    }

/** Handles access denied errors.
 * 
 * @param e the access denied exception
 * @return a forbidden response 
*/
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse.of(403, "FORBIDDEN", "Access denied"));
    }

/** Handles upload size exceed errors.
 * 
 * @param e the upload size exception
 * @return a payload too large response 
*/
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxUpload(MaxUploadSizeExceededException e) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(ErrorResponse.of(413, "PAYLOAD_TOO_LARGE", "Upload exceeds the maximum allowed size"));
    }

/** Handles unexpected exceptions that are not explicitly handled 
 * by another exception handler.
 * 
 * @param e the unexpected exception
 * @return an internal-server-error response 
 */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception e) {
        log.error("Unhandled exception", e);
        return ResponseEntity.internalServerError()
                .body(ErrorResponse.of(500, "INTERNAL_ERROR", "Something went wrong"));
    }

/** Returns the validation message for a field error.
 * 
 * @param fe the Spring validation field error
 * @return the configured validation message
 * when no message is available 
 */
    private static String messageOf(FieldError fe) {
        return fe.getDefaultMessage() != null ? fe.getDefaultMessage() : "invalid value";
    }


}
