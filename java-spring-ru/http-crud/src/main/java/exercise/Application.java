package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/posts")
    List<Post> getLimitPosts(@RequestParam(defaultValue = "1") Long page,
                             @RequestParam(defaultValue = "30") int limitOfPosts) {
        return posts.stream()
                .skip((page * limitOfPosts) - ((long) limitOfPosts))
                .limit(limitOfPosts)
                .toList();
    }

   @GetMapping("/posts/{id}")
    Post getCurrentPost(@PathVariable int id) {
        Optional<Post> currentPost = Optional.of(posts.get(id));
        return currentPost.orElseGet(null);
   }

   @PostMapping("/posts")
    Post addPost(@RequestBody Post post) {
        if (!(post == null)) {
            posts.add(post);
        }
        return post;
   }

   @PutMapping("posts/{id}")
    Post updatePost(@RequestBody Post post, @PathVariable String id) {
        var currentPost = posts.stream()
                .filter(i -> i.getId().contains(id))
                .findFirst();
        if (currentPost.isPresent()) {
            Post p = currentPost.get();
            p.setId(post.getId());
            p.setBody(post.getBody());
            p.setTitle(post.getTitle());
        }
        return post;
   }

   @DeleteMapping("/posts/{id}")
    void deletePost(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
   }
}
