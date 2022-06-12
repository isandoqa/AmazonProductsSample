package sample.ishaq.amazon.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import sample.ishaq.amazon.extensions.formatDate

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "created_at")
    val createdAt: String?,

    @Json(name = "price")
    val price: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "uid")
    val uID: String?,

    @Json(name = "image_ids")
    val imageIds: List<String>?,

    @Json(name = "image_urls")
    val imageUrls: List<String>?,

    @Json(name = "image_urls_thumbnails")
    val imageThumbnails: List<String>?,

){
    fun getFormattedDate():String{
        return createdAt?.let {
            it.formatDate(
                format = "yyyy-MM-dd hh:mm:ss",
                requiredFormat = "yyyy - MM - dd"
            )?:""
        }?:""
    }

    fun getThumbnailUrl():String?{
        return imageThumbnails?.firstOrNull()
    }
    fun getImageUrl():String?{
        return imageUrls?.firstOrNull()
    }

}
