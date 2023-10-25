## Модифицирующие формы

### Ссылки

* [Методы работы с контекстом](https://javalin.io/documentation#context)
* [Подключение стилей Bootstrap](https://getbootstrap.com/docs/5.1/getting-started/introduction/#css)
* [Класс StringUtils из библиотеки Apache Commons](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html#startsWithIgnoreCase-java.lang.CharSequence-java.lang.CharSequence-)

### src/main/java/exercise/App.java

Реализуйте обработчики для маршрутов, которые необходимы для создания нового пользователя:

* GET */users/build* отображает страницу с формой, которую заполняет пользователь
* POST */users* обрабатывает данные формы

Перед сохранением данных от пользователей в репозиторий, нормализуйте их:

* Имя и фамилию пользователя нужно [капитализировать](https://en.wikipedia.org/wiki/Capitalization)
* Электронную почту нужно привести к нижнему регистру и удалить концевые пробелы

В целях безопасности мы должны хранить пароль пользователя в репозитории в зашифрованном виде. Перед сохранением пароля зашифруйте его с помощью уже созданного метода `Security.encrypt()`.

### src/main/java/exercise/dto/users/UsersPage.java

Создайте дата-класс для передачи списка пользователей в шаблон.

### src/main/jte/users/build.jte

Реализуйте шаблон для формы создания нового пользователя.

### Подсказки

* Для работы с данными используйте методы класса `UserRepository`
* Если хотите сделать красивый вывод в шаблонах, используйте классы Bootstrap
* Присмотритесь к методам класса `StringUtils` — возможно, какой-то из них вам понадобится
