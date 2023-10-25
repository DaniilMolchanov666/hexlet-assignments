@import exercise.model.User
@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <a href="/users">Добавить Пользователя</a>
        <a href="">Показать пользователей</a>

    `
)
