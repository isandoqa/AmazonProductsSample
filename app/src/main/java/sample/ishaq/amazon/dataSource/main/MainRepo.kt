package sample.ishaq.amazon.dataSource.main

import sample.ishaq.amazon.model.ProductsResponse
import sample.ishaq.amazon.model.apiResult.ApiResult

interface MainRepo {
    suspend fun loadMainProductsList():ApiResult<ProductsResponse?>
}