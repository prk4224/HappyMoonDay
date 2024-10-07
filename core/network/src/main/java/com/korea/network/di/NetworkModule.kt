package com.korea.network.di

import com.korea.network.api.MuseumSearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val API_KEY = "6b49677a67707075373169594f4f53"
    private const val BASE_URL = "http://openapi.seoul.go.kr:8088/$API_KEY/"

    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideMuseumSearchApi(retrofit: Retrofit): MuseumSearchApi {
        return retrofit.create(MuseumSearchApi::class.java)
    }
}
