@import exercise.dto.posts.*
@param PostPage page

<html lang="en">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
<body>
<table class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">NAME</th>
        <th scope="col">BODY</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${page.getPost().getId()}</td>
        <td>${page.getPost().getName()}</td>
        <td>${page.getPost().getBody()}</td>
    </tr>
    </tbody>
</table>
<form action="/post/${page.getPost().getId()}/edit" method="get">
    <input type="submit" value="Update">
</form>
<form action="/posts/${page.getPost().getId()}" method="get">
    <input type="submit" value="Delete">
</form>
    <br>
        <a href="/posts"> Back </a>
    </body>
</html>
