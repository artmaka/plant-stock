package org.example.stock.exception;

/**
 * Исключение, выбрасываемое при попытке создать растение, которое уже существует.
 */
public class PlantAlreadyExistsException extends RuntimeException {
    public PlantAlreadyExistsException(String message) {
        super(message);
    }
}
