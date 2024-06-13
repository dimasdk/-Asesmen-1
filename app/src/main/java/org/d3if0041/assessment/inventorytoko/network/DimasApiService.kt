package org.d3if0041.assessment.inventorytoko.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if0041.assessment.inventorytoko.model.Dimas
import org.d3if0041.assessment.inventorytoko.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://api.dimasdk.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DimasApiService {
    @GET("json.php")
    suspend fun getDimas(
        @Query("auth") userId: String
    ): List<Dimas>

    @Multipart
    @POST("json.php")
    suspend fun postDimas(
        @Part("auth") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("stok") stok: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("json.php")
    suspend fun deleteDimas(
        @Query("auth") userId: String,
        @Query("id") id: String
    ): OpStatus
}

object DimasApi {
    val service: DimasApiService by lazy {
        retrofit.create(DimasApiService::class.java)
    }

    fun getDimasUrl(image: String): String {
        return "${BASE_URL}$image"
    }
}
enum class ApiStatus { LOADING, SUCCESS, FAILED }