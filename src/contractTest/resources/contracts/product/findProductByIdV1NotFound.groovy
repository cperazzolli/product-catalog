package contracts.product

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method GET()
        headers {
            accept "application/json"
        }
        url("/api/v1/products/bb7259ca-fa7f-4807-8ad4-7df3fd5e174f")
    }
    response {
        status 404
        headers {
            contentType "application/problem+json"
        }
        body([
                instance: fromRequest().path(),
                type: "/errors/not-found",
                title: "Not found"
        ])
    }
}