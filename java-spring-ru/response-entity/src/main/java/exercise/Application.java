package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getCurrentPost(@PathVariable String id) {
        if (posts.stream().anyMatch(i -> i.getId().equals(id))) {

            var post = posts.stream()
                    .filter(i -> i.getId().equals(id))
                    .findFirst()
                    .get();

            return ResponseEntity.ok()
                    .body(post);
        } else {
            return ResponseEntity.status(404)
                    .body(null);
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> addNewPost(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(201).body(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id,
                                           @RequestBody Post post) {
        if (!posts.contains(post)) {
            return ResponseEntity.status(204).body(post);
        }
        Post foundedPost = posts.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();

        foundedPost.setId(post.getId());
        foundedPost.setBody(post.getBody());
        foundedPost.setTitle(post.getTitle());

        return ResponseEntity.ok().body(foundedPost);
    }

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
