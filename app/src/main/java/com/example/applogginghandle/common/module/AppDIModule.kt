package com.example.applogginghandle.common.module

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.example.applogginghandle.common.logging_event.domain.DeleteEventUseCase
import com.example.applogginghandle.common.logging_event.domain.LoadFailedEventsUseCase
import com.example.applogginghandle.common.model.Constants
import com.example.applogginghandle.data.database.EventDao
import com.example.applogginghandle.data.database.EventDatabase
import com.example.applogginghandle.data.database.LocalRepositoryImpl
import com.example.applogginghandle.data.repository.EventApi
import com.example.applogginghandle.data.repository.RemoteRepositoryImpl
import com.example.applogginghandle.domain.LocalRepository
import com.example.applogginghandle.domain.PostEventUseCase
import com.example.applogginghandle.domain.RemoteRepository
import com.example.applogginghandle.domain.SaveFailedEventUseCase
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
object AppDIModule {

    @Provides
    @Singleton
    fun providesTaskDatabase(@ApplicationContext context: Context): EventDatabase {
        return Room.databaseBuilder(
            context,
            EventDatabase::class.java,
            EventDatabase.DATA_BASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesDao(database: EventDatabase): EventDao {
        return database.taskDao
    }

    @Provides
    @Singleton
    fun providesEventApi(): EventApi {
        val interceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(employeesApi: EventApi): RemoteRepository {
        return RemoteRepositoryImpl(employeesApi)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(dao: EventDao): LocalRepository {
        return LocalRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun providePostEventUseCase(remoteRepository: RemoteRepository): PostEventUseCase {
        return PostEventUseCase(remoteRepository)
    }

    @Provides
    @Singleton
    fun provideSaveFailedEventUseCase(dao: EventDao): SaveFailedEventUseCase {
        return SaveFailedEventUseCase(dao)
    }

    @Provides
    @Singleton
    fun provideDeleteEventUseCase(dao: EventDao): DeleteEventUseCase {
        return DeleteEventUseCase(dao)
    }

    @Provides
    @Singleton
    fun provideLoadFailedEventsUseCase(dao: EventDao): LoadFailedEventsUseCase {
        return LoadFailedEventsUseCase(dao)
    }

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }


}