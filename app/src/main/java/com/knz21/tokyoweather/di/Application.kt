package com.knz21.tokyoweather.di

import com.knz21.tokyoweather.api.WeatherApiService
import com.knz21.tokyoweather.view.WeatherActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                    .baseUrl("http://weather.livedoor.com")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Singleton
    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService =
            retrofit.create(WeatherApiService::class.java)
}

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun inject(activity: WeatherActivity)
}