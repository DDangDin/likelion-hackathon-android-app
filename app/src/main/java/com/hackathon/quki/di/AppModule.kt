package com.hackathon.quki.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.hackathon.quki.data.repository.CategoryRepositoryImpl
import com.hackathon.quki.data.repository.MainRepositoryImpl
import com.hackathon.quki.data.repository.UserRepositoryImpl
import com.hackathon.quki.data.source.local.CategoryDao
import com.hackathon.quki.data.source.local.CategoryDatabase
import com.hackathon.quki.data.source.remote.ApiConstants
import com.hackathon.quki.data.source.remote.QukiApi
import com.hackathon.quki.domain.repository.CategoryRepository
import com.hackathon.quki.domain.repository.MainRepository
import com.hackathon.quki.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCategoryDao(categoryDatabase: CategoryDatabase) = categoryDatabase.categoryDao()

    @Provides
    @Singleton
    fun provideCategoryDatabase(@ApplicationContext context: Context): CategoryDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CategoryDatabase::class.java,
            "category_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(
        dao: CategoryDao
    ): CategoryRepository {
        return CategoryRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideQukiApi(): QukiApi {
        fun String?.isJsonObject(): Boolean {
            return this?.startsWith("{") == true && this.endsWith("}")
        }

        fun String?.isJsonArray(): Boolean {
            return this?.startsWith("[") == true && this.endsWith("]")
        }

        val client = OkHttpClient
            .Builder()
//            .readTimeout(25, TimeUnit.SECONDS)
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .writeTimeout(15, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("Retrofit_Log", message)
//            when {
//                message.isJsonObject() ->
//                    Log.d("Retrofit_Log", JSONObject(message).toString(4))
//
//                message.isJsonArray() ->
//                    Log.d("Retrofit_Log", JSONObject(message).toString(4))
//
//                else -> {
//                    try {
//                        Log.d("Retrofit_Log", JSONObject(message).toString(4))
//                    } catch (e: Exception) {
//                        Log.d("Retrofit_Log", message)
//                    }
//                }
//            }
        }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(ApiConstants.QUKI_SERVER_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QukiApi::class.java)
    }


    @Provides
    @Singleton
    fun provideUserRepository(
        api: QukiApi
    ): UserRepository {
        return UserRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        api: QukiApi
    ): MainRepository {
        return MainRepositoryImpl(api)
    }
}