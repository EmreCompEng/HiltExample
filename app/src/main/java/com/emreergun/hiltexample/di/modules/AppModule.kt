package com.emreergun.hiltexample.di.modules

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.emreergun.hiltexample.R
import com.emreergun.hiltexample.SessionManager
import com.emreergun.hiltexample.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Uygulama boyu canlı kalan modüldür
// App Seviyesindedir

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Retrofit Instance
    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// Retrofit çağrışarını observable türüne dönüştürür
            .build()
    }

    @Singleton
    @Provides
    fun provideRequestOpitons(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.ic_emre_icon)
            .error(R.drawable.ic_error_image)
    }

    // Glide Instance
    @Singleton
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun provideAppDrawable( application: Application): Drawable {
        return ContextCompat.getDrawable(application,R.drawable.ic_emre_icon)!!
    }

    @Singleton
    @Provides
    fun provideSessionManager(): SessionManager {
        return SessionManager
    }



}