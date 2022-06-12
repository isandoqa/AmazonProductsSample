package sample.ishaq.amazon.extensions

import java.lang.Exception
import java.text.SimpleDateFormat

fun String.formatDate(
    format:String,
    requiredFormat:String
):String?{
    return try {
        val sdf= SimpleDateFormat(format)
        val date= sdf.parse(this)
        val required= SimpleDateFormat(requiredFormat)
        required.format(date)
    }catch (e: Exception){
        e.printStackTrace()
        null
    }
}