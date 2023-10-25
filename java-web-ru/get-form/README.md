## Поисковые формы

### Ссылки

* [Методы работы с контекстом](https://javalin.io/documentation#context)
* [Подключение стилей Bootstrap](https://getbootstrap.com/docs/5.1/getting-started/introduction/#css)
* [Класс StringUtils из библиотеки Apache Commons](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html#startsWithIgnoreCase-java.lang.CharSequence-java.lang.CharSequence-)

### src/main/java/exercise/App.java

Реализуйте обработчик, который будет обрабатывать GET-запросы на адрес */users* и формировать список пользователей. Обработчик должен поддерживать фильтрацию через параметр *term*, в котором передается имя пользователя *firstName*.

В итоге обработчик должен выдавать все совпадения по началу имени пользователя без учета регистра. Список пользователей доступен в переменной *$users*.

Список пользователей `List<User>` находится в константе `USERS`.

### src/main/java/exercise/dto/users/UsersPage.java

Реализуйте дата-класс для передачи данных в шаблоны.

### src/main/jte/users/index.jte

Реализуйте вывод списка пользователей и формы для фильтрации. Если совпадения не найдены, то должна выводится только форма. Поле ввода форма должна сохранять введенное значение.

### Подсказки

* Если хотите сделать красивый вывод в шаблонах, используйте классы Bootstrap
* Присмотритесь к методам класса `StringUtils` — возможно, какой-то из них вам понадобится
