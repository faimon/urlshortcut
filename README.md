# URL shortCut - сервис для сокращения длинных url ссылок.
[![Build Status](https://travis-ci.org/faimon/urlshortcut.svg?branch=master)](https://travis-ci.org/faimon/urlshortcut)
### Приложение работает через REST API, для авторизации используется JWT token.
**Используемые технологии:** 
* Java 14.
* Spring (Boot, Data, Security).
* Hibernate.
* PostgreSQL.

## API

#### /registration
* `POST` `{ site : "Ваш сайт" }` : Зарегистрироваться в системе. 
Ответом будет `{ registration : true/false, login: УНИКАЛЬНЫЙ_КОД, password : УНИКАЛЬНЫЙ_КОД}`

#### /login
* `POST` `{ login: "логин", password: "пароль" }` : Авторизоваться в системе. 
Ответ от сервера будет содержать JWT token для авторизации в блоке HEAD:
`Authorization: Bearer e25d31c5-db66-4cf2-85d4-8faa8c544ad6`

#### /convert
* `POST` `{ url : "URL ссылка" }` : Получить сокращенную ссылку. 
Ответ от сервера: `{ code : "УНИКАЛЬНЫЙ_КОД" }`

#### /redirect/УНИКАЛЬНЫЙ_КОД
* `GET` : Получить URL ссылку по сокращенной. 
Ответ от сервера в заголовке: `HTTP CODE - 302 REDIRECT URL`

#### /statistic
* `GET` : Получить статистику вызовов каждого Url. 
Ответ от сервера в JSON :
`{
     {url : "https://stackoverflow.com/questions/3931696/", total : 110},
     {url : "https://mail.ru/", total : 436}
 }`
