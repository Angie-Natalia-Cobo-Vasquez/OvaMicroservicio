package com.example.ovaservices.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message, String resourceName, Long id) {
        super(String.format("%s: %s con ID %d no encontrado", message, resourceName, id));
    }

    // Opcional: mantener constructor simple también
    public ResourceNotFoundException(String message) {
        super(message);
    }
}