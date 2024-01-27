package exercise.controller;

import exercise.model.Post;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.List;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    public PostRepository postRepository;

    @Autowired
    public CommentRepository commentRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostComment(@PathVariable long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post with id " + id + " not found!"));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Post addPost(@RequestBody Post c) {
        postRepository.save(c);
        return c;
    }

    @PutMapping("/{id}")
    public Post updatePost(@RequestBody Post p, @PathVariable long id) {
        var post = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post with id " + id + " not found!"));
        post.setBody(p.getBody());
        post.setTitle(p.getTitle());
        post.setCreatedAt(p.getCreatedAt());
        return post;
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id) {
        commentRepository.deleteByPostId(id);
        postRepository.deleteById(id);
    }
}
