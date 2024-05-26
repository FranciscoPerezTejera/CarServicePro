package com.carservicepro.exceptionhandler;



import com.carservicepro.exceptionhandler.customexception.VehicleNotFoundException;
import com.carservicepro.exceptionhandler.dto.ApiError;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ApiError> handleModelNotFoundException(VehicleNotFoundException modelNotFoundException) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, modelNotFoundException.getMessage());
        return new ResponseEntity<>(apiError,apiError.getStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "The model code cannot be null and must be unique.");
        return new ResponseEntity<>(apiError,apiError.getStatus());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleMissingServletRequestParameterException(MissingServletRequestParameterException missingServletRequestParameterException) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "the request param name is not correct");
        return new ResponseEntity<>(apiError,apiError.getStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "There was an internal server error.");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "Error reading JSON message.";

        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) ex.getCause();
            errorMessage = formatInvalidFormatException(ife);
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errorMessage);
        return ResponseEntity.badRequest().body(apiError);
    }

    private String formatInvalidFormatException(InvalidFormatException ife) {
        String fieldName = ife.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .reduce((first, second) -> second)
                .orElse("Unknown");
        String invalidValue = ife.getValue().toString();
        return String.format("Format error in field '%s'. Invalid value: '%s'", fieldName, invalidValue);
    }
}