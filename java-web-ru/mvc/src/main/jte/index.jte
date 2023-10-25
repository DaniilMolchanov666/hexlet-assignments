@import exercise.util.NamedRoutes;

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <main>
                  <a href="${NamedRoutes.postsPath()}">Все посты</a>
            </main>
        </div>
    `
)
