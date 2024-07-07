package org.example.stock.exception;

/**
 * Исключение, выбрасываемое при наличии неверных данных о растении.
 */
public class InvalidPlantDataException extends RuntimeException {
    public InvalidPlantDataException(String message) {
        super(message);
    }
}
