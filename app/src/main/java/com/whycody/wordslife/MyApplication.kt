package com.whycody.wordslife

import android.app.Application
import com.whycody.wordslife.data.dataModule
import com.whycody.wordslife.data.languageModule
import com.whycody.wordslife.data.repositoryModule
import com.whycody.wordslife.data.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@MyApplication)
            modules(dataModule, repositoryModule, languageModule, viewModelsModule)
        }
    }
}