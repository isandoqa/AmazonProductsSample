package sample.ishaq.amazon.api

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import sample.ishaq.amazon.R
import sample.ishaq.amazon.model.ProductsResponse
import sample.ishaq.amazon.model.apiResult.ApiResult
import sample.ishaq.amazon.model.apiResult.ResultWrapper
import javax.inject.Inject

class ApiHelperImp @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
): ApiHelper{
    override suspend fun loadProducts(): ApiResult<ProductsResponse?> {
        val result= callApiSafely(
            dispatcher= dispatcher,
            ProductsResponse::class.java
        ){
            apiService.loadProducts()
        }

        return parseResult(result)
    }


    private fun <T> parseResult(
        result: ResultWrapper<T>
    ): ApiResult<T?>{
        return when(result){
            is ResultWrapper.Success -> {
                ApiResult(
                    data = result.value,
                )
            }

            is ResultWrapper.GenericError ->{
                ApiResult(errorMessage = context.getString(R.string.general_error))
            }

            is ResultWrapper.NetworkError ->{
                ApiResult(errorMessage = "Network error")
            }
        }
    }
}