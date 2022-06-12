package sample.ishaq.amazon.model.apiResult

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError<out T>(val code: Int? = null, val error: T? = null): ResultWrapper<T>()
    object NetworkError: ResultWrapper<Nothing>()
}