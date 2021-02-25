package nz.co.asb.network

import nz.co.asb.App
import nz.co.asb.BuildConfig
import nz.co.asb.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitClient @Inject constructor(private val app: App) {

    private lateinit var retrofit: Retrofit

    fun initRetrofit(url: String? = null): Retrofit {
        retrofit = Retrofit.Builder().apply {
            if (url != null) {
                baseUrl(url)
            } else {
                baseUrl(Constants.BASE_URL)
            }
            client(getOkHttpClient())
            addConverterFactory(GsonConverterFactory.create())
        }.build()
        return retrofit
    }

    private fun getOkHttpClient(): OkHttpClient {

        return OkHttpClient.Builder().apply {
            connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)

            if (BuildConfig.LOGCAT_ENABLED) {
                interceptors().add(getLoggingInterceptor())
            }
        }.build()
    }

    private fun getLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}
