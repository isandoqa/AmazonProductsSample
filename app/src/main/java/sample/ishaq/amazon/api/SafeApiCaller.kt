package sample.ishaq.amazon.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import sample.ishaq.amazon.model.apiResult.ResultWrapper
import java.io.IOException
import java.lang.reflect.Type

suspend inline fun <reified T> callApiSafely(
    dispatcher: CoroutineDispatcher,
    type: Type,
    crossinline apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher){
        try {
            ResultWrapper.Success(apiCall.invoke())
        }catch (throwable: Throwable){
            throwable.printStackTrace()
            when(throwable){
                is IOException -> ResultWrapper.NetworkError
                is HttpException ->{
                    val code = throwable.code()
                    val errorResponse = convertErrorBody<T>(throwable, type)
                    ResultWrapper.GenericError(code, errorResponse)
                }
                else ->{
                    ResultWrapper.GenericError(null,null)
                }
            }
        }
    }
}

inline fun <reified T> convertErrorBody(throwable: HttpException, type: Type): T? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter<T>(Types.newParameterizedType(T::class.java,type))
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        exception.printStackTrace()
        null
    }
}