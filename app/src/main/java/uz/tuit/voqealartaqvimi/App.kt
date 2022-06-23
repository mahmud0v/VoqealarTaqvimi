package uz.tuit.voqealartaqvimi

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }



}