@import exercise.util.NamedRoutes;

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <form action="${NamedRoutes.usersPath()}" method="post">
                <div class="mb-3">
                    <label class="form-label">
                    Имя
                    <input type="text" class="form-control" name="firstName" />
                    </label>
                </div>
                <div class="mb-3">
                    <label class="form-label">
                    Фамилия
                    <input type="text" class="form-control" name="lastName" />
                    </label>
                </div>
                <div class="mb-3">
                    <label class="form-label">
                    Email
                    <input type="email" class="form-control" required name="email" />
                    </label>
                </div>
                <div class="mb-3">
                    <label class="form-label">
                    Пароль
                    <input type="password" class="form-control" required name="password" />
                    </label>
                </div>
                <input type="submit" class="btn btn-primary" value="Зарегистрировать" />
            </form>
        </div>
    `
)
