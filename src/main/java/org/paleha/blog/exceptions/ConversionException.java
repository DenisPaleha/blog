package org.paleha.blog.exceptions;

public class ConversionException extends Exception {
    // The class allows to handle conversion errors when decoding to decimal system
    // from all others number systems.
    public ConversionException(String message) {
        super(message);
    }
}