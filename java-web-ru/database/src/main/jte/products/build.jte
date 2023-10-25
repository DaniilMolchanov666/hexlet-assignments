@import exercise.dto.products.BuildProductPage
@import exercise.util.NamedRoutes;
@param BuildProductPage page

@template.layout.page(

    content = @`
        @if(page.getErrors() != null)
            <div class="mb-3">
                <ul>
                    @for(var validator : page.getErrors().values())
                        @for(var error : validator)
                            <li>${error.getMessage()}</li>
                        @endfor
                    @endfor
                </ul>
            </div>
        @endif

        <div class="mx-auto p-4 py-md-5">
            <form action="${NamedRoutes.productsPath()}" method="post">
                <div class="mb-3">
                    <label class="form-label">
                        Название
                        <input type="text" class="form-control" name="title" value="${page.getTitle()}" />
                    </label>
                </div>
                <div class="mb-3">
                    <label class="form-label">
                        Цена
                        <input type="text" class="form-control" name="price" value="${page.getPrice()}" />
                    </label>
                </div>
                <input type="submit" class="btn btn-primary" value="Сохранить" />
            </form>
        </div>
    `,
)
