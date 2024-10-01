package com.app.chat.controller;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class WebSocketErrorHandler {

    @MessageExceptionHandler
    public void handleException(Message<?> message, Throwable exception) {
        System.err.println("Error handling message: " + message);
        exception.printStackTrace();
    }
}