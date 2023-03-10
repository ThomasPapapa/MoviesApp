package com.thomas.data.client

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal class APIClient(private val context: Context) {

    private val BASE_URL = "https://api.tvmaze.com/"

    private lateinit var httpClient: OkHttpClient
    private lateinit var gsonConverterFactory: GsonConverterFactory
    lateinit var retrofit: Retrofit

    init {
        setUpOkHttpClient()
        setUpGsonConverterFactory()
        setUpRetrofitClient()
    }

    private fun setUpRetrofitClient() {
        retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(gsonConverterFactory)
            .client(httpClient).build()
    }

    private fun setUpGsonConverterFactory() {
        val gson = GsonBuilder().disableHtmlEscaping().setLenient().create()
        gsonConverterFactory = GsonConverterFactory.create(gson)
    }

    private fun setUpOkHttpClient() {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        //setup for caching and offline access
        val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB
        val myCache = Cache(context.cacheDir, cacheSize)
        val onlineInterceptor = Interceptor { chain ->
            if (chain.request().header("Cache-Control") != "no-cache") {
                val response = chain.proceed(chain.request())
                val maxAge =
                    60 // read from cache for 60 seconds even if there is internet connection
                response.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .removeHeader("Pragma")
                    .build()
            } else {
                chain.proceed(chain.request())
            }

        }
        val offlineInterceptor = Interceptor { chain ->
            var request: Request = chain.request()
            if (!context.isInternetAvailable() && chain.request()
                    .header("Cache-Control") != "no-cache"
            ) {
                val maxStale = 60 * 60 * 24 * 1 // Offline cache available for 1 day
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
            }
            chain.proceed(request)
        }

        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.readTimeout(45, TimeUnit.SECONDS)
        httpClientBuilder.connectTimeout(45, TimeUnit.SECONDS)
        httpClientBuilder.addInterceptor(logging)
        httpClientBuilder.addInterceptor(offlineInterceptor)
        httpClientBuilder.addNetworkInterceptor(onlineInterceptor)
        httpClientBuilder.cache(myCache)
        httpClient = httpClientBuilder.build()
    }


}

fun Context.isInternetAvailable(): Boolean {
    var result = false
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
    } else {
        cm?.run {
            cm.activeNetworkInfo?.run {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    result = true
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    result = true
                }
            }
        }
    }
    return result
}