# Тайный санта
RESTful API сервис для игры в Тайного Санту!

## Стек технологий
Docker, Docker Compose, Java, Spring Boot, Spring Data JPA, PostgreSQL, etc.

## Установка и запуск
Убедитесь, что у вас установлен [Docker](https://www.docker.com/).

Склонируйте репозиторий с помощью следующей команды:
```shell
git clone https://github.com/arsuhinars/secret_santa
```

Внутри директории репозитория создайте `.env` файл с конфигурацией сервиса. Ниже приведен пример файла:
```text
POSTGRES_DB = secret_santa
POSTGRES_USER = user
POSTGRES_PASSWORD = qwerty12
```

Теперь запустите сервис с помощью Docker:
```shell
docker compose up -d
```

Для остановки сервиса введите следующую команду:
```shell
docker compose down
```

## Документация
OpenAPI схема доступна [здесь](/api_schema.yml).

