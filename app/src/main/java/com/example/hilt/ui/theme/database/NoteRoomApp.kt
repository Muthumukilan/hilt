package com.example.hilt.ui.theme.database

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteRoomApp:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}