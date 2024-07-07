package org.example.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Глобальный обработчик исключений.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обработка всех общих исключений.
     *
     * @param ex исключение
     * @return ответ с сообщением исключения и HTTP статусом 500 (Внутренняя ошибка сервера)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Обработка PlantNotFoundException.
     *
     * @param ex исключение PlantNotFoundException
     * @return ответ с сообщением исключения и HTTP статусом 404 (Не найдено)
     */
    @ExceptionHandler(PlantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handlePlantNotFoundException(PlantNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Обработка PlantAlreadyExistsException.
     *
     * @param ex исключение PlantAlreadyExistsException
     * @return ответ с сообщением исключения и HTTP статусом 409 (Конфликт)
     */
    @ExceptionHandler(PlantAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handlePlantAlreadyExistsException(PlantAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Обработка InvalidPlantDataException.
     *
     * @param ex исключение InvalidPlantDataException
     * @return ответ с сообщением исключения и HTTP статусом 400 (Некорректный запрос)
     */
    @ExceptionHandler(InvalidPlantDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidPlantDataException(InvalidPlantDataException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
