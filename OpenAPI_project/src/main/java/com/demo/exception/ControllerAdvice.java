package com.demo.exception;


import com.demo.dto.ErrorDTO;
import com.demo.enums.CustomEnums;
import com.demo.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {
    /**
     * <p>     * This method handles Exception and gives custom response     * </p>
     * ** @param req httpServletRequest request
     * * @param e   Exception
     * * @return ResponseEntity <ApiResponse>
     * * @see Exception
     * * @see ApiResponse
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(HttpServletRequest req, Exception e) {
        log.info("handleException {}", e);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(),
                CustomEnums.SOMETHING_WENT_WRONG.getValue(), req.getServletPath());
        ApiResponse apiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
        log.error("handleException ::", e);
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

    /**
     * <p>     * This Method handles CustomException and returns ApiResponse     * </p>     *     * @param req httpServletRequest request     * @param e   CustomException     * @return ResponseEntity <ApiResponse>     * @see CustomException     * @see ApiResponse
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(HttpServletRequest req, CustomException e) {
        HttpStatus httpStatus = e.getHttpStatus();
        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(), e.getMessage(), req.getServletPath());
        errorDTO.setError(e.getMessage());
        errorDTO.setMessage(httpStatus.name());
        ApiResponse apiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
        log.error("handleCustomException :: ", e);
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(HttpServletRequest req, IllegalArgumentException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(),
                CustomEnums.SOMETHING_WENT_WRONG.getValue(), req.getServletPath());
        ApiResponse apiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
        log.error("handleException ::", e);
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(HttpServletRequest req,
                                                                             MethodArgumentNotValidException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<String> errorMessages = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(error -> errorMessages.add(error.getDefaultMessage()));

        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(), httpStatus.name(), req.getServletPath(),
                errorMessages);
        ApiResponse apiResponse = new ApiResponse(httpStatus, httpStatus.name(), errorDTO);
        log.error("handleMethodArgumentNotValidException :: ", e);
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }



}

