package com.codereview.linkgenerator.exceptions;

public class ShortLinkNotFoundException extends RuntimeException{
    public ShortLinkNotFoundException(String message) {
        super(message);
    }
}
