# Plant Stock Application

Это пример приложения для управления складскими запасами растений, разработанное на базе Spring Boot с использованием PostgreSQL в качестве базы данных.

## Требования

- Java 11 или выше
- PostgreSQL 16 или выше

## Установка

### Шаг 1: Сборка приложения

1. Клонируйте репозиторий:

   ```bash
   git clone https://github.com/artmaka/plant-stock.git
   cd Plant_Stock

2. Соберите приложение с помощью Maven:
	```bash
   ./mvnw clean package
 
3. Запустите PostgreSQL базу данных и настройте соединение в application.properties:
   ```bash
   	spring.datasource.url=jdbc:postgresql://localhost:5432/YOUR_DATABASE
	spring.datasource.username=YOUR_USERNAME
	spring.datasource.password=YOUR_PASSWORD
   
4. Запустите приложение при помощи Shift+F10

После успешного запуска приложения вы сможете открыть браузер и перейти по адресу http://localhost:8080/plants для работы с приложением.
