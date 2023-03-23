package com.kodin.mobilbank.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kodin.mobilbank.data.AppDataBaseHilt
import com.kodin.mobilbank.data.network.MyApiEndPont
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val TAG = "BankingModule"



@Module
@InstallIn(SingletonComponent::class)
object BankingModule {

    private const val BASE_URL = "http://192.168.1.38:8080/"



    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()


            .create()
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {

            level = HttpLoggingInterceptor.Level.BODY
        }



    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: IBankingModule): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)


            .addInterceptor( httpLoggingInterceptor)
            .build()



    @Singleton
    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            // .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create()     )
            .addConverterFactory(GsonConverterFactory.create(provideGson()))



            .client(okHttpClient)
            .build()
    }



    @Singleton
    @Provides
    fun providerUserApiClient(retrofit: Retrofit): MyApiEndPont {
        return retrofit.create(MyApiEndPont::class.java)

    }



    @Singleton
    @Provides
    fun provideSomeString(app: Application) : IBankingModule { //     fun provideSomeString(@ApplicationContext appContext: Context) : IFacturaModule {
        Log.d(TAG, "provideSomeString: provideSomeString" +app.hashCode())
        return  iFImpl(app)
    }





    @Provides
    @Singleton
    fun provideRoomDi(app: Application): AppDataBaseHilt {
        Log.d(TAG, "provideRoomDi: provideRoomDi "+app.hashCode())
        return Room.databaseBuilder(
            app , AppDataBaseHilt::class.java, QUOTE_DATABASE_NAME_clean

        )
            .build()
    }

    private const val QUOTE_DATABASE_NAME_clean = "database_bank"

    @Singleton
    @Provides
    fun provideUserDaoDi(db: AppDataBaseHilt) = db.getFromDataBaseUserDaoTable()




}