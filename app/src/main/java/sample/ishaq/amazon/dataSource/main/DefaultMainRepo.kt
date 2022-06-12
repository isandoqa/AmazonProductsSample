package sample.ishaq.amazon.dataSource.main

import sample.ishaq.amazon.api.ApiHelper
import sample.ishaq.amazon.model.Product
import sample.ishaq.amazon.model.ProductsResponse
import sample.ishaq.amazon.model.apiResult.ApiResult
import javax.inject.Inject

class DefaultMainRepo @Inject constructor(
    private val apiHelper: ApiHelper
):MainRepo {
    override suspend fun loadMainProductsList(): ApiResult<ProductsResponse?> {
        return apiHelper.loadProducts()
    }
}