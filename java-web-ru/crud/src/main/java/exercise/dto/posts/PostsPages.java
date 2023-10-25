package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;

public class PostsPages {
    private final List<Post> postList;

    private final int index;
    public PostsPages(List<Post> listOfPosts, int index) {
        postList = listOfPosts;
        this.index = index;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public int getIndex() {
        return index;
    }
}


