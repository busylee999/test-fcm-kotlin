package ru.busylee.testfcm;

import android.app.Application;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import ru.busylee.share.gcm.MyGcmListenerService;
import ru.busylee.testfcm.module.CommonModule

/**
 * Created by busylee on 16.08.16.
 */

public class MainApplication : Application() {
    var mApplicationComponent : ApplicationComponent? = null


    @Singleton
    @Component(modules = arrayOf(CommonModule::class))
    interface ApplicationComponent {
        fun inject(gcmService:MyGcmListenerService)
    }

    override fun onCreate() {
        super.onCreate()

        mApplicationComponent = DaggerMainApplication_ApplicationComponent.builder()
                .commonModule(CommonModule())
                .build()
    }

    val component: ApplicationComponent get() = mApplicationComponent!!
}
