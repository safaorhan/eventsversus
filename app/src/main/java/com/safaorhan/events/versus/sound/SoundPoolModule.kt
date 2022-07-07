package com.safaorhan.events.versus.sound

import android.media.AudioAttributes
import android.media.AudioAttributes.Builder
import android.media.SoundPool
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.text.Typography.dagger


@Module
@InstallIn(SingletonComponent::class)
class SoundPoolModule {

    @Provides
    fun provideSoundPool(): SoundPool {
        val audioAttributes = Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        return SoundPool.Builder()
            .setMaxStreams(2)
            .setAudioAttributes(audioAttributes)
            .build()
    }
}