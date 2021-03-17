package d.offrede.core.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.chucknorris.io/"

val retrofitModule = module {

    single<GsonConverterFactory> { GsonConverterFactory.create(get<Gson>()) }

    single { get<GsonBuilder>().create() }

    single { GsonBuilder() }

    single {
        get<OkHttpClient.Builder>()
            .addInterceptor(get<Interceptor>(named("logger")))
            .addInterceptor(get<Interceptor>(named("headers")))
            .build()
    }

    single { OkHttpClient.Builder() }

    single<Interceptor>(named("logger")) {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        logger
    }

    single(named("headers")) {
        Interceptor { chain ->
            var request = chain.request()

            val builder = request
                .newBuilder()
                .addHeader("Content-Type", "application/json;charser=UTF-8")

            request = builder.build()
            chain.proceed(request)
        }
    }

    single<Retrofit> {
        get<Retrofit.Builder>(named("retrofit-builder"))
            .client(get())
            .addConverterFactory(get<GsonConverterFactory>())
            .baseUrl(BASE_URL)
            .build()
    }

    single(named("retrofit-builder")) {
        Retrofit.Builder()
    }
}