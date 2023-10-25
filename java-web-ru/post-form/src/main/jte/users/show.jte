@import exercise.dto.users.UsersPage
@import exercise.model.User
@import gg.jte.Content
@param UsersPage page

<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">

    <title>List Of Users</title>

    <a href="users/build">Добавить Пользователя</a>

</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">Email</th>
        <th scope="col">FirstName LastName</th>
    </tr>
    </thead>
    <tbody>
    @if(page.getUsers().isEmpty())
        <tr>
        <th scope="row">  </th>
        <td>  </td>
        <td>  </td>
        </tr>
    @endif
    @for(User user: page.getUsers())
        <tr>
            <th scope="row"> ${user.getId()} </th>
            <td> ${user.getEmail()} </td>
            <td> ${user.getFirstName()} ${user.getLastName()}</td>
        </tr>
    @endfor
    </tbody>
</table>
</body>
</html>
