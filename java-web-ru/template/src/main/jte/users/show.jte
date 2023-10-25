@import java.util.List
@import exercise.model.User
@param List<User> users

<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
    <head>
        <p1> List Of All Users: </p1>
    </head>
    <body>
    <table class="table table-striped">
        <thead class="table-light">
            <tr>
                <th scope="col">id</th>
                <th scope="col">Email</th>
                <th scope="col">FirstName</th>
                <th scope="col">LastName</th>
            </tr>
        </thead>
            <tbody>
                    @for(var user: users)
                    <tr>
                        <th scope="row"> <a href="users/${user.getId()}"> ${user.getId()} </a></th>
                        <th scope="row"> ${user.getEmail()} </th>
                        <th scope="row"> ${user.getFirstName()} </th>
                        <th scope="row"> ${user.getLastName()} </th>
                    </tr>
                    @endfor
            </tbody>
        </table>
    </body>
</html>