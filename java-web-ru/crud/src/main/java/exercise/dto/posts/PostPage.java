package exercise.dto.posts;

import exercise.model.Post;

public class PostPage {
    private Post post;

    public PostPage(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}
