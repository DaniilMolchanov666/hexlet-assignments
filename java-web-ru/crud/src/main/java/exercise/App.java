package exercise;

import io.javalin.Javalin;
import exercise.controller.PostsController;
import exercise.controller.RootController;
import exercise.util.NamedRoutes;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get(NamedRoutes.rootPath(), RootController::index);

        app.get(NamedRoutes.showPosts(), PostsController::showAll);
        app.get(NamedRoutes.showPost(), PostsController::showPost);

        app.get(NamedRoutes.updatePost(), PostsController::update);
        app.post(NamedRoutes.showPost(), PostsController::updatePost);

        app.delete(NamedRoutes.showPost(), PostsController::deletePost);

        app.get(NamedRoutes.createPost(), c -> c.render("posts/create.jte"));
        app.post(NamedRoutes.createPost(), PostsController::createPost);

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
