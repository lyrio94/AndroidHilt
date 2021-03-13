package br.iesb.mobile.androidhilt.di

import br.iesb.mobile.androidhilt.domain.Location
import br.iesb.mobile.androidhilt.domain.RickAndMortyPage
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object RickAndMortyModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

        val gsonConfig = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gsonConfig))
            .build()
    }

    @Provides
    fun provideRickAndMortyRetrofitService(retrofit: Retrofit): RickAndMortyService =
        retrofit.create(RickAndMortyService::class.java)

}

interface RickAndMortyService {

    @GET("character")
    @Headers("Content-Type: application/json")
    suspend fun characterList(@Query("page") page: Int): RickAndMortyPage

    @GET("location/{id}")
    @Headers("Content-Type: application/json")
    suspend fun location(@Path("id") id: Int): Location
}
