package com.safaorhan.events.versus.events

import com.safaorhan.events.Events
import com.safaorhan.events.navigation.NavigationEventHandler
import com.safaorhan.events.snackbar.SnackbarEventHandler
import com.safaorhan.events.toast.ToastEventHandler
import com.safaorhan.events.versus.events.dialog.AlertDialogEventHandler
import com.safaorhan.events.versus.events.sound.SoundEventHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventsModule {

    @Singleton
    @Provides
    fun provideEvents(
        soundEventHandler: SoundEventHandler,
        alertDialogEventHandler: AlertDialogEventHandler
    ): Events =
        Events.Builder()
            .registerHandler(SnackbarEventHandler)
            .registerHandler(ToastEventHandler)
            .registerHandler(NavigationEventHandler)
            .registerHandler(soundEventHandler)
            .registerHandler(alertDialogEventHandler)
            .build()
}