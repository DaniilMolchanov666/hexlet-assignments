@import exercise.dto.users.*
@param UsersPage page

@template.
    content = @`
        <a href="/users/build">New User</a>
        @for(var user : page.getUsers())
            <div>${user.getId()}</div>
        @endfor
`
)
