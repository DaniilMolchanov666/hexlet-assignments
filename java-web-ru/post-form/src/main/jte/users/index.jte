@import exercise.dto.users.*
@param UsersPage page

@template.layout.page(
    content = @`
        <div class="mb-3">
            <a href="/users/build">Зарегистрировать нового пользователя</a>
            <a href="/users">Показать пользователей</a>
    `
)
