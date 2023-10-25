@import exercise.dto.products.ProductsPage
@import exercise.util.NamedRoutes
@param ProductsPage page

@template.layout.page(

    page = page,
    content = @`
        <h1>Все товары</h1>
        <div class="mb-3">
            <a href="${NamedRoutes.buildProductPath()}">Добавить новый товар</a>
        </div>
        <table class="table table-striped">
            @for(var product : page.getProducts())
                <tr>
                    <td>
                        ${product.getId()}
                    </td>
                    <td>
                        <a href="${NamedRoutes.productPath(product.getId())}">${product.getTitle()}</a>
                    </td>
                    <td>
                        ${product.getPrice()}
                    </td>
                </tr>
            @endfor
        </table>
    `,
)
