# Calendar Rest service
### *(Spring Boot + Data JPA + PostgreSQL + Swagger + JUnit)*

<br/>
Программа представляет собой серверную часть приложения (без UI части) по работе с пользователями и событиями, связанными с их календарем.
Предоставляет REST API для:

* получения списка всех пользователей (авторов событий);

* создания, получения (по id), удаления, редактирования пользователя;

* создания, получения (по id), удаления, редактирования события пользователя в календаре;

* получения списка всех событий в календаре пользователя;

* поиска событий пользователя за определенный промежуток времени;


Документация к работе сервиса оформлена с помощью Swagger и доступна во время работы программы по адресу 
[swagger-ui.html](http://localhost:8080/swagger-ui.html/).
<br/>

<br/>


## Запуск приложения
1. Git [clone](https://github.com/Laboulaye/calendar-rest-service.git)
2. Создать базу данных *calendar*
3. В файле *resources/application.properties* изменить данные *username/password* на свои

<br/>

![image1](src/main/resources/static/screen/2.png)
<br/>

![image2](src/main/resources/static/screen/1.png)
<br/>

![image3](src/main/resources/static/screen/3.png)
