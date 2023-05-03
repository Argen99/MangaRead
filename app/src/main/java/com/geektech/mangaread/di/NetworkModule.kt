package com.geektech.mangaread.di

import com.geektech.data.BuildConfig.BASE_URL
import com.geektech.data.local_db.prefs.TokenManager
import com.geektech.data.remote.api_service.AuthApiService
import com.geektech.data.remote.api_service.CommentsApiService
import com.geektech.data.remote.api_service.MainApiService
import com.geektech.data.remote.api_service.MangaApiService
import com.geektech.data.remote.retrofit.AuthAuthenticator
import com.geektech.data.remote.retrofit.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { AuthAuthenticator(get<MainApiService>(), get<TokenManager>()) }
    factory { AuthInterceptor(get<TokenManager>()) }
    factory { provideOkHttpClient() }
    factory { provideMainApi(get()) }
    factory { provideMangaApi(get()) }
    single { provideRetrofit(get()) }

    single<AuthApiService> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(interceptor)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(get<AuthInterceptor>())
                    .authenticator(get<AuthAuthenticator>())
                    .build()
            )
            .build()
            .create(AuthApiService::class.java)
    }

    single<CommentsApiService> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(interceptor)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(get<AuthInterceptor>())
                    .authenticator(get<AuthAuthenticator>())
                    .build()
            )
            .build()
            .create(CommentsApiService::class.java)
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
}

fun provideMainApi(retrofit: Retrofit): MainApiService {
    return retrofit.create(MainApiService::class.java)
}

fun provideMangaApi(retrofit: Retrofit): MangaApiService {
    return retrofit.create(MangaApiService::class.java)
}
