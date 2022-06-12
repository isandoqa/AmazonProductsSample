package sample.ishaq.amazon.api

import sample.ishaq.amazon.model.Product
import sample.ishaq.amazon.model.ProductsResponse
import sample.ishaq.amazon.model.apiResult.ApiResult

interface ApiHelper {
    suspend fun loadProducts():ApiResult<ProductsResponse?>
}