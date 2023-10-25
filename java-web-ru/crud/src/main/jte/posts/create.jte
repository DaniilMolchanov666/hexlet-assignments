<html lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
      crossorigin="anonymous">
<body>
<form action="/create" method="post">
    <div>
        <label>
            Name
            <input type="text" name="Name" />
        </label>
    </div>
    <div>
        <label>
            Body
            <input type="text" name="Body" />
        </label>
    </div>
    <input type="submit" value="Save" />
</form>
</body>
</html>