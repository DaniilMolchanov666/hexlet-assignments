package exercise.controller;

import exercise.repository.PostRepository;
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

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    public CommentRepository commentRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment getCurrentComment(@PathVariable long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Comment with id " + id + " not found!"));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Comment addComment(@RequestBody Comment c) {
        commentRepository.save(c);
        return c;
    }

    @PutMapping("/{id}")
    public Comment updateComment(@RequestBody Comment c, @PathVariable long id) {
        var com = commentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Comment with id " + id + " not found!"));
        com.setPostId(c.getPostId());
        com.setBody(c.getBody());
        com.setPostId(c.getPostId());
        return com;
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable long id) {
        commentRepository.deleteById(id);
    }
}
