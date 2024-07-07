package org.example.stock.exception;

/**
 * Исключение, выбрасываемое, если растение не найдено.
 */

public class PlantNotFoundException extends RuntimeException {
    public PlantNotFoundException(String message) {
        super(message);
    }
}
