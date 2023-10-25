@import exercise.model.User
@param User user

@template.layout.page(
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <div class="mb-3">
                Имя: ${user.getFirstName()}
            </div>
            <div class="mb-3">
                Фамилия: ${user.getLastName()}
            </div>
            <div class="mb-3">
                Email: ${user.getEmail()}
            </div>
        </div>
    `
)
