## JPA Entity

В этом упражнении мы продолжим работу с CRUD сущностей. Но данные нашего приложения будут храниться в базе данных. Для работы с ними мы будем использовать Spring Data JPA.

### Полезные ссылки

* [Аннотации Jakarta Persistence](https://jakarta.ee/specifications/persistence/3.0/apidocs/jakarta.persistence/jakarta/persistence/package-summary)

### build.gradle.kts

Подключите в приложение базу данных *H2*.

### src/main/java/exercise/model/Person.java

Создайте модель `Person` со свойствами, которая будет представлять человека в нашем приложении. У человека есть уникальный идентификатор, имя *firstName* и фамилия *lastName*. Идентификатор должен генерироваться автоматически.

### src/main/java/exercise/repository/PersonRepository.java

Создайте репозиторий для работы с сущностью `Person`.

### src/main/java/resources/application.yml

Настройте приложение так, чтобы при старте приложения создавалась и обновлялась схема базы данных.

### src/main/java/exercise/controller/PeopleController.java

В контроллере для примера уже создан обработчик для маршрута просмотра конкретного человека. Добавьте в приложение обработчики для следующих маршрутов:

* *GET /people* — список всех персон
* *POST /people* – создание новой персоны
* *DELETE /people/{id}* – удаление персоны
