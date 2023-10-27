package com.compose.movieappcompose.data.dependency_injection

import com.compose.movieappcompose.data.remote.MovieApi
import com.compose.movieappcompose.data.repository.MovieRepositoryImpl
import com.compose.movieappcompose.domain.repository.MovieRepository
import com.compose.movieappcompose.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DI {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }
    //Dikkat ! canlıda olan uygulamada HttpLoggingInterceptor kullanılmamalı verileri loglama yaptığı için tehlikeye atabilir.!
    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS) // Bağlantı kurma süresi
            .readTimeout(60, TimeUnit.SECONDS) // Veri okuma süresi
            .writeTimeout(60, TimeUnit.SECONDS) // Veri gönderme süresi
            .build()
    }


    @Provides
    @Singleton
    fun provideMovieApi(okHttpClient: OkHttpClient) : MovieApi{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi) : MovieRepository{
        return MovieRepositoryImpl(api = api)
    }

}