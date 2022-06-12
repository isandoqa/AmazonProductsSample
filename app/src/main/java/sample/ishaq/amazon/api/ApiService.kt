package sample.ishaq.amazon.api

import retrofit2.http.GET
import sample.ishaq.amazon.model.ProductsResponse

interface ApiService {
    @GET("dynamodb-writer")
    suspend fun loadProducts():ProductsResponse
}