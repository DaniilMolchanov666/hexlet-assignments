package exercise.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPages;
import exercise.model.Post;
import exercise.repository.PostRepository;
import io.javalin.http.Context;

public class PostsController {
    private static final int LIMIT_OF_POSTS_ON_PAGE = 5;
    public static void showAll(Context c) {
        int index = c.queryParamAsClass("index", Integer.class).getOrDefault(1);

        List<Post> sortListOfPosts = PostRepository.getEntities().stream()
                .sorted(Comparator.comparing(Post::getId))
                .skip((long) index * LIMIT_OF_POSTS_ON_PAGE)
                .limit(LIMIT_OF_POSTS_ON_PAGE)
                .toList();

        PostsPages posts = new PostsPages(sortListOfPosts, index);
        c.render("posts/show.jte", Collections.singletonMap("posts", posts));
    }

    public static void showPost(Context context) {
        long id = context.pathParamAsClass("id", Long.class).get();

        Post post = PostRepository.getEntities().stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(null);

        PostPage page = new PostPage(post);
        context.render("posts/info.jte", Collections.singletonMap("page", page));
    }

    public static void deletePost(Context context) {
        int id = context.pathParamAsClass("id", Integer.class).get();
        PostRepository.removeById(id);
        context.redirect("/posts");
    }

    public static void createPost(Context context) {
        String name = context.formParamAsClass("Name", String.class).getOrDefault("");
        String body = context.formParamAsClass("Body", String.class).getOrDefault("");

        if (name.isBlank() || body.isBlank()) {
            context.redirect("/create");
            return;
        }

        Post post = new Post(name, body);
        PostRepository.save(post);
        context.redirect("/");

    }

    public static void updatePost(Context context) {
        long id = context.pathParamAsClass("id", Long.class).getOrDefault(0L);
        String name = context.formParamAsClass("Name", String.class).getOrDefault("");
        String body = context.formParamAsClass("Body", String.class).getOrDefault("");

        PostRepository.change(id, name, body);

        context.redirect("/posts");
    }

    public static void update(Context context) {
        long id = context.pathParamAsClass("id", Long.class).getOrDefault(0L);
        context.render("posts/update.jte", Collections.singletonMap("id", id));

    }

}
