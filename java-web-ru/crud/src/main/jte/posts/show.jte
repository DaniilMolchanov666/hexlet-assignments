@import exercise.dto.posts.*
@import exercise.model.Post
@import exercise.repository.PostRepository
@param PostsPages posts

<html lang="eu">
<body>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
      crossorigin="anonymous">
<table class="table">
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">Name</th>
    </tr>
    </thead>
    <tbody>
    @for(Post post: posts.getPostList())
        <tr>
            <th scope="row"> ${post.getId()} </th>
            <td> <a href="/posts/${post.getId()}"> ${post.getName()}</a> </td>
        </tr>
    @endfor
    </tbody>
</table>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item"><a class="page-link" href="/posts?index=${(posts.getIndex() - 1) <= 0 ? 1:
        posts.getIndex() - 1 }">Previous</a></li>
        <li class="page-item"><a class="page-link" href="/posts?index=${((posts.getIndex() + 1) * 5) >=
        PostRepository.getEntities().size() ? posts.getIndex(): (posts.getIndex() + 1)}">Next</a></li>
    </ul>
</nav>
<a href="/"> Back </a>
</body>
</html>