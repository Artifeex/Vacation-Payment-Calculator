package ru.sandr.vacationpaymentcalc.http.handler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sandr.vacationpaymentcalc.dto.ErrorResponseDto;
import ru.sandr.vacationpaymentcalc.utils.ResponseErrorWrapper;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice(basePackages = "ru.sandr.vacationpaymentcalc.http")
public class RestControllerExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseErrorWrapper handleValidationException(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ErrorResponseDto> errors = bindingResult.getAllErrors().stream()
                .map(error -> {
                    if(error instanceof FieldError) {
                        FieldError fieldError = (FieldError) error;
                        return new ErrorResponseDto(fieldError.getField(), fieldError.getDefaultMessage());
                    } else  {
                        return new ErrorResponseDto(error.getObjectName(), error.getDefaultMessage());
                    }
                }).collect(Collectors.toList());
        return new ResponseErrorWrapper(HttpStatus.BAD_REQUEST.value(), errors);
    }
}
