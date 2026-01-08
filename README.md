# Трекер рабочих активностей

Простое веб-приложение для учёта времени, потраченного на работу и перерывы.

## Технологии

- Backend: Spring Boot 4 (Java 17+), Spring Data JPA, Hibernate, PostgreSQL
- Frontend: Vue.js 3 (Composition API внутри setup()), native HTML + JS без сборки

## Требования

- Java 17 или выше
- PostgreSQL 13+
- Gradle

## Настройка и запуск сервера

1. Создать базу данных PostgreSQL:
   ```sql
   CREATE DATABASE activitydb;
   ```
2. Настроить подключение в src/main/resources/application-dev.properties (при локальном развертывании) или в src/main/resources/application-prod.properties (при развертывании на сервере):
    ```spring.datasource.url=jdbc:postgresql://localhost:5432/activitydb
    spring.datasource.username=postgres
    spring.datasource.password=your_password_here
    
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```
3. Собрать и запустить проект:
    ```
   ./gradlew bootRun
    ```
   Приложение будет доступно по адресу http://localhost:8080

## Запуск клиентской части
Клиент - обычный статический файл, обслуживается самим Spring Boot.

Просто открыть в браузере:
http://localhost:8080/

Интерфейс сразу готов к работе.

## REST API
Базовый путь: /api/intervals
1. Получить все интервалы
   ```
   curl http://94.142.138.52/activitytracker/api/intervals
    ```
2. Добавить новый интервал
    ```
   curl -X POST http://94.142.138.52/activitytracker/api/intervals \
   -H "Content-Type: application/json" \
   -d '{
   "startSeconds": 54000,
   "endSeconds": 61200,
   "type": "WORK"
   }
    ```
  