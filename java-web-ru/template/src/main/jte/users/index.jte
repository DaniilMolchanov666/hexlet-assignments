@import exercise.model.User
@param User user
@param Integer id

<html>
<head>
    <p1>
        User with id: ${user.getId()}
    </p1>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
        <body>
            <table class="table table-striped">
                <thead class="table-ligth">
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">Email</th>
                        <th scope="col">FirstName</th>
                        <th scope="col">LastName</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row"> ${user.getId()} </th>
                        <th scope="row"> ${user.getEmail()} </th>
                        <th scope="row"> ${user.getFirstName()} </th>
                        <th scope="row"> ${user.getLastName()} </th>
                    </tr>
                </tbody>
            </table>
        </body>
</html>
