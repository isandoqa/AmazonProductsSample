package sample.ishaq.amazon.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductsResponse(
    @Json(name = "results")
    val products: List<Product>
)
