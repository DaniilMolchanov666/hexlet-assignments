package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import exercise.model.Post;
import exercise.Data;

@RestController
@RequestMapping("/api")
public class PostsController {

    public List<Post> list = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> getPosts(@PathVariable String id) {
        return list.stream()
                .filter(post -> post.getUserId() == Integer.parseInt(id))
                .toList();
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post addPost(@PathVariable int id, @RequestBody Post post) {
        post.setUserId(id);
        list.add(post);
        return post;
    }
}
