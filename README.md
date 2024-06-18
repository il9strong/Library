Инструкция запуска проекта

Настройка базы данных:
1) Убедитесь, что у вас установлен PostgreSQL и запущена база данных.
2) Создайте в базе данных необходимые таблицы с использованием предоставленного SQL скрипта:
CREATE TABLE book (
    id BIGINT PRIMARY KEY,
    isbn VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255),
    description TEXT,
    author VARCHAR(255) NOT NULL
);

CREATE TABLE library_record (
    id BIGSERIAL PRIMARY KEY,
    book_id BIGINT NOT NULL,
    borrowed_at TIMESTAMP NOT NULL,
    due_at TIMESTAMP,
    returned_at TIMESTAMP,
    FOREIGN KEY (book_id) REFERENCES book(id)
);

Настройка проекта:
1) Склонируйте проект и перейдите в корневую директорию проекта.
2) Настройте application.properties для подключения к вашей базе данных PostgreSQL.

Запуск приложения:
Запустите приложение с помощью Maven: mvn spring-boot:run

Использование Swagger UI:
Откройте документацию Swagger UI по адресу: http://localhost:8080/swagger-ui.html
 
