package ru.netology.yamapspoints.application

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import ru.netology.yamapspoints.BuildConfig


class App: Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.MAPS_API_KEY)
    }
}