package com.emreergun.hiltexample.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // Hilt App Seviyesinde tetiklenmesi sağlandı
class BaseApplication : Application() {
}