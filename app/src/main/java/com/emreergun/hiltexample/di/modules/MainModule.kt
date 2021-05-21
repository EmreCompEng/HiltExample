package com.emreergun.hiltexample.di.modules

import android.app.Application
import androidx.recyclerview.widget.LinearLayoutManager
import com.emreergun.hiltexample.ui.main.post.PostAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @Provides
    fun provideLayoutManager(application: Application): LinearLayoutManager {
        return LinearLayoutManager(application, LinearLayoutManager.VERTICAL,false)
    }

    @Provides
    fun providePostAdapter(): PostAdapter {
        return PostAdapter()
    }


}