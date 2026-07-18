package contracts.product

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        headers {
            accept 'application/json'
            contentType 'application/json'
        }
        urlPath("/api/v1/products") {
        body([
                name: value(
                        test("Notebook X11"),
                        stub(nonBlank())
                ),
                brand: value(
                        test("Deep Driver"),
                        stub(nonBlank())
                ),
                regularPrice: value(
                        test(1500.00),
                        stub(number())
                ),
                salePrice: value(
                        test(1000.00),
                        stub(number())
                ),
                enabled: value(
                        test(true),
                        stub(anyBoolean())
                ),
                categoryId: value(
                        test("bb99e5b9-8cea-49cb-a4e1-32868193cd6b"),
                        stub(anyUuid())
                ),
                description: value(
                        test("A Gamer Notebook"),
                        stub(nonBlank())
                )
          ])
        }
    }
    response {
        status 201
        headers {
            contentType "application/json"
        }
        body([
                id: anyUuid(),
                addedAt: anyIso8601WithOffset(),
                name: fromRequest().body('$.name'),
                brand: fromRequest().body('$.brand'),
                regularPrice: fromRequest().body('$.regularPrice'),
                salePrice: fromRequest().body('$.salePrice'),
                inStock: false,
                enabled: fromRequest().body('$.enabled'),
                category: [
                        "id": fromRequest().body('$.categoryId'),
                        "name": "Notebook"
                ],
                description: fromRequest().body('$.description')
        ])
    }
}