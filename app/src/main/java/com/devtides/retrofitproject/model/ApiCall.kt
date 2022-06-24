package com.devtides.retrofitproject.model

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface ApiCall {

    @GET("apiCall")
    fun callGet(): Call<ApiCallResponse>

    @POST("apiCall")
    fun callPost(): Call<ApiCallResponse>

    @GET("apiCall?name=Alex&age=25")
    fun callQueryStatic(): Call<ApiCallResponse>

    @GET("apiCall")
    fun callQueryDynamic(@Query("name") n: String?, @Query("age") a: Int?): Call<ApiCallResponse>


    @GET("apiCall")
    fun callQueryMultiple(@QueryMap params: Map<String, String>): Call<ApiCallResponse>


    @GET("https://example.com/user/info")
    fun callUrlBypass(): Call<ApiCallResponse>

    @GET()
    fun callUrlDynamic(@Url url: String): Call<ApiCallResponse>

    //    @GET("user/{info}")
    @GET("{info}")
    fun callUrlPath(@Path("info") info: String): Call<ApiCallResponse>

    @POST("apiCall")
    fun callPost(@Body user: User): Call<ApiCallResponse>

    @FormUrlEncoded
    @POST("apiCall")
    fun callFormData(
        @Field("first_name") firstName: String, @Field("last_name")
        lastName: String
    ): Call<ApiCallResponse>

    @FormUrlEncoded
    @POST("apiCall")
    fun callFormData(@FieldMap fields:Map<String, String>): Call<ApiCallResponse>

    @Headers("Cache-Control: max-age=3600", "user-agent: abc")
    @GET("apiCall")
    fun callHeadersStatic():Call<ApiCallResponse>

    @GET("apiCall")
    fun callHeadersDynamic(@Header("user-name") userName:String):Call<ApiCallResponse>

    @GET("apiCall")
    fun callHeadersMultiple(@HeaderMap headers:Map<String, String>):Call<ApiCallResponse>

    @GET("apiCall")
    fun callGetRx():Single<ApiCallResponse>

}