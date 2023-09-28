package com.example.cbd.apiGateway.exceptions;

public class MessagingErrorException extends Exception {
    public MessagingErrorException() {
        super();
    }

    public MessagingErrorException(String message) {
        super(message);
    }
}
