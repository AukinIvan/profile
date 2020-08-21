# Профили

#### Тестовое задание

В качестве тестового задания необходимо реализовать RESTfull  приложение

* При разработке использовать spring boot остальное на ваше усмотрение, желательно придерживаться экосистемы spring
* Использовать в качестве базы Postgres
* Cross-origin должно быть отключено
* Доступ к сервису возможен только при наличием токена ‘secret’ во всех остальных случаях кроме “GET /exit” возвращать 401 название и реализация на ваше усмотрение, инструкция для передачи токена должна прилагаться в месте с тестовым заданием
* Сервис должен запускатся на 8010 порту
* Name и email должны быть регистронезависимые
* Добавить фильтр при регистрации на проверку уникальности поля email в случае если Email есть в базе возвращать 409 статус
* Должен прилагаться артефакт приложения
* Для данного приложения реализуйте и подключил OpenApi (swagger)
* Версия java не выше 11
* Сборщик Maven


### Endpoints:

**POST /profiles/set**
----
Создает запись профиля и присваивает ему id

**Request:** принимает json следующей структурой

```
{
	“name”: string
	“email”: string
	“age”: int
}
```
**Responses:**
в случае успеха возвращает id записи пользователя

```
status 200
{
	“idUser”: int
}
```

В случае не корректного email

```
status 400
{
	“msg”: string
}
```

В случае если email уже передавался (реализовать через фильтр)

```
status 403
{
	“msg”: string
}
```

GET /profiles/last
----
Возвращает последний созданный профиль

**Responses:**

```
status 200
{
        “id”: int
        “name”: string
        “email”: string
        “age”: int
        “created”: timestamp
}
```

GET /profiles
----
Возвращает все созданные профили

**Responses:**

```
status 200
[{
        “id”: int
        “name”: string
        “email”: string
        “age”: int
        “created”: timestamp
}...]
```

GET /profiles/{ID}
----
Возвращает профиль по его ID

**Responses:**

```
status 200
{
        “id”: int
        “name”: string
        “email”: string
        “age”: int
        “created”: timestamp
}
```

В случае если запись не найдена

```
status 404 
{
	“msg”: string
}
```

POST /profiles/get
----
Возвращает профиль по email

**Request:**
принимает json следующей структурой:

```
{
	“email”: string
}
```

**Responses:**

```
status 200
{
        “id”: int
        “name”: string
        “email”: string
        “age”: int
        “created”: timestamp
}
```

В случае если запись не найдена

```
status 404 
{
	“msg”: string
}
```

GET /error/last
----
Возвращает сообщение последней ошибки

**Responses:**

```
status 200
{
	“msg”: string
	“created”: timestamp
}
```

GET /exit
----
Производит закрытия приложение с редиректом на страницу /exit-success (название вариативно) с надписью ‘приложение закрыто’ допускаются и другие варианты информирования о закрытие.