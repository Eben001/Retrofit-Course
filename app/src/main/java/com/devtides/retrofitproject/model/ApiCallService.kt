package com.devtides.retrofitproject.model

import android.content.Context
import com.devtides.retrofitproject.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiCallService {

    private const val BASE_URL = "https://us-central1-apis2-e78c3.cloudfunctions.net/"


    private var api: ApiCall? = null
    private fun getApi(context: Context): ApiCall {

        if (api == null) {
            val okHttpClient = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            if (BuildConfig.DEBUG) {
                okHttpClient.addInterceptor(logging)

            }

            val cacheSize = 5 * 1024 * 1024L
            val cache = Cache(context.cacheDir, cacheSize)
            okHttpClient.cache(cache)

            api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(ApiCall::class.java)
        }
        return api!!

    }

    private fun getApiRx() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //takes the http request and converts it to a single
            .build()
            .create(ApiCall::class.java)


    fun callRx() = getApiRx()

    fun call(context: Context) =
        getApi(context).callGet()
//    getApi(context).callGet()
    //getApi(context).callPost()
    //getApi(context).callQueryStatic()
    //getApi(context).callQueryDynamic("Ebenezer", 4)
//        getApi(context).callQueryDynamic("Ebenezer", 24)
    //getApi(context).callQueryMultiple(hashMapOf(Pair("a", "value1"), Pair("b", "value2")))
    //getApi(context).callUrlBypass()
    //getApi(context).callUrlDynamic("https://example.com/user/")
//        getApi(context).callUrlPath("info")
//        getApi(context).callUrlPath("apiCall")
//        getApi(context).callPost(User("Ebenezer", "123456"))
//        getApi(context).callFormData(firstName = "Daniel", lastName = "Isaac")
//        getApi(context).callFormData(hashMapOf(Pair("name", "Ebenezer"), Pair("last_name", "Gana")))
//        getApi(context).callHeadersStatic()

//        getApi(context).callHeadersDynamic("Micheal")

//        getApi(context).callHeadersMultiple(hashMapOf(Pair("user-agent", "abc"), Pair("username", "Mike")))


}