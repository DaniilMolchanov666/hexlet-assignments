package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    public static String showPosts() { return "/posts"; }

    public static String showPost() { return "/posts/{id}"; }

    public static String updatePost() { return "/post/{id}/edit"; }

    public static String createPost() { return "/create"; }


}
