package contracts.product

import org.springframework.cloud.contract.spec.Contract


Contract.make {
    request {
        method GET()
        headers {
            accept  'application/json'
        }
        url("/api/v1/products/bb99e5b9-8cea-49cb-a4e1-32868193cd6b")
    }
    response {
        status 200
        headers {
            contentType "application/json"
        }
        body([
                id: fromRequest().path(3),
                addedAt: anyIso8601WithOffset(),
                name: "Notebook X11",
                brand: "Deep Driver",
                regularPrice: 1500.00,
                salePrice: 1000.00,
                inStock: true,
                enabled: true,
                category: [
                        id: anyUuid(),
                        name: "Notebook"
                ],
                description: "A Gamer Notebook"
        ])
    }
}

