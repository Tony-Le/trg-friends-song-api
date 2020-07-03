package io.github.trgfriendscovers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerRuntimeAdvice {
    @ResponseBody
    @ExceptionHandler(ControllerRuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String coverNotFoundHandler(ControllerRuntimeException ex) {
        return ex.getMessage();
    }
}
