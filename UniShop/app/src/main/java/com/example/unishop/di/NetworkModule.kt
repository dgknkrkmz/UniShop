package com.example.unishop.di

import com.example.unishop.data.datasources.remote.ApiService
import com.example.unishop.data.repo.ProductRepository
import com.example.unishop.data.repo.ProductRepositoryImpl
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
object NetworkModule {
    private const val  BASE_URL= "http://kasimadalan.pe.hu/"  // API'nin ana adresi

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor= HttpLoggingInterceptor().apply {
            //Logcat'te tüm istek ve cevapları görmemizi sağlar (Hata ayıklama için kullanabiliriz)
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }


    // Retrofit Sağlayıcısı
    // API isteği göndermek için ana kısmımız burası
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        // Hilt, provideOkHttpClient'ı çağırıp oluşan OkHttpClient'ı buraya otomatik verir.
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())// JSON'dan objeye çevir
            .build()
    }


    //ApiService sağlayıcısı bu kısımda
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    //  Repository Sağlayıcısı
    @Provides
    @Singleton
    fun provideProductRepository(impl: ProductRepositoryImpl): ProductRepository {
        // Hilt'e, ProductRepository arayüzü istendiğinde ProductRepositoryImpl sınıfını kullanmasını söylüyoruz.
        return impl
    }

}

