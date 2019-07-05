package com.motonaka.googlebookssample

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.motonaka.googlebookssample.api.GoogleBooksApi
import com.motonaka.googlebookssample.presentation.SecondViewModel
import com.motonaka.googlebookssample.repository.GoogleBooksRepository
import com.motonaka.googlebookssample.repository.GoogleBooksRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@MyApplication)
            // modules
            modules(myModule)
        }
    }
}

val myModule = module {
    single { createOkHttpClient() }
    single { createRetrofit(get()) }

    single<GoogleBooksRepository> { GoogleBooksRepositoryImpl(get()) }

    viewModel { SecondViewModel(get()) }
}

private fun createOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor).build()
}

private fun createRetrofit(okHttpClient: OkHttpClient): GoogleBooksApi {
    return Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/books/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GoogleBooksApi::class.java)
}