package com.superlcb

import android.app.Application
import com.superlcb.util.AppUtil

class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        AppUtil.applicationContext=this.applicationContext
    }
}