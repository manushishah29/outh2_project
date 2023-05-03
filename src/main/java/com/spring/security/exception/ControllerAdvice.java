package com.spring.security.exception;


import com.spring.security.dto.ErrorDTO;
import com.spring.security.enums.CustomEnums;
import com.spring.security.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {
    /**     * <p>     * This method handles Exception and gives custom response     * </p>     *     * @param req httpServletRequest request     * @param e   Exception     * @return ResponseEntity <ApiResponse>     * @see Exception     * @see ApiResponse     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(HttpServletRequest req, Exception e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(),
                CustomEnums.SOMETHING_WENT_WRONG.getValue(), req.getServletPath());
        ApiResponse ApiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
        log.error("handleException ::", e);
        return ResponseEntity.status(httpStatus).body(ApiResponse);
    }
    /**     * <p>     * This Method handles CustomException and returns ApiResponse     * </p>     *     * @param req httpServletRequest request     * @param e   CustomException     * @return ResponseEntity <ApiResponse>     * @see CustomException     * @see ApiResponse     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(HttpServletRequest req, CustomException e) {
        HttpStatus httpStatus = e.getHttpStatus();
        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(), e.getMessage(), req.getServletPath());
        errorDTO.setError(e.getMessage());
        errorDTO.setMessage(httpStatus.name());
        ApiResponse ApiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
        log.error("handleCustomException :: ", e);
        return ResponseEntity.status(httpStatus).body(ApiResponse);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleException(HttpServletRequest req, AccessDeniedException e) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(),
                CustomEnums.FORBIDDEN.getValue(), req.getServletPath());
        ApiResponse ApiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
        log.error("handleException ::", e);
        return ResponseEntity.status(httpStatus).body(ApiResponse);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> handleException(HttpServletRequest req, BadCredentialsException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(),
                CustomEnums.BAD_REQUEST.getValue(), req.getServletPath());
        ApiResponse ApiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
        log.error("handleException ::", e);
        return ResponseEntity.status(httpStatus).body(ApiResponse);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleException(HttpServletRequest req, IllegalArgumentException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(),
                CustomEnums.SOMETHING_WENT_WRONG.getValue(), req.getServletPath());
        ApiResponse ApiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
        log.error("handleException ::", e);
        return ResponseEntity.status(httpStatus).body(ApiResponse);
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


//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ApiResponse> handleIllegalArgumentException(HttpServletRequest req,
//                                                                      IllegalArgumentException e) {
//        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
//        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(),
//                ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), req.getServletPath());
//        ApiResponse ApiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
//        log.error("handleIllegalArgumentException :: ", e);
//        return ResponseEntity.status(httpStatus).body(ApiResponse);
//    }
}
