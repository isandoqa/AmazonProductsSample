package sample.ishaq.amazon.model.apiResult

data class ApiResult<T>(
    val errorMessage: String?=null,
    val data: T?= null
)
