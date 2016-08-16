package ru.busylee.testfcm.module

import dagger.Module

/**
 * Created by busylee on 16.08.16.
 */
@Module
class CommonModule {
    fun provideInt(): Int {
        return 1
    }
}