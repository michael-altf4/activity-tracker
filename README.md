# Трекер рабочих активностей

**Живое демо в интернете:**  
🔗 **http://94.142.138.52/activitytracker/**

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
2. Настроить подключение в src/main/resources/application-dev.properties (при локальном развертывании):
    ```spring.datasource.url=jdbc:postgresql://localhost:5432/activitydb
    spring.datasource.username=postgres
    spring.datasource.password=your_password_here
    
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```
   или в src/main/resources/application-prod.properties (при развертывании на сервере)

    ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/activitydb
   spring.datasource.username=userexample
   spring.datasource.password=password

   spring.jpa.show-sql=false
   ```

3. Собрать и запустить проект:
    ```
   ./gradlew bootRun
    ```
   Приложение будет активно и доступно.

## Запуск клиентской части локально
Клиент - обычный статический файл, обслуживается самим Spring Boot.

Просто открыть в браузере:
http://localhost:8080/

Интерфейс сразу готов к работе.

## Развёртывание в продакшене
Приложение развёрнуто на VPS (Ubuntu) и доступно в интернете по адресу:
🔗 http://94.142.138.52/activitytracker/

- Запускается как systemd-сервис (activitytracker.service);
- Автоматически стартует при загрузке сервера;
- Работает за Nginx (reverse proxy на порту 80);
- Используется профиль prod.

## REST API
Базовый путь: /api/intervals. Папка приложения activitytracker
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
  