package com.safaorhan.events.versus.sound

import android.content.Context
import android.media.SoundPool
import com.safaorhan.events.versus.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SoundManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val soundPool: SoundPool
) {
    private var errorBeep: Int? = null

    fun playErrorBeep() {
        if (errorBeep == null) {
            errorBeep = soundPool.load(context, R.raw.error_beep, 1)
            soundPool.setOnLoadCompleteListener { _, _, _ ->
                soundPool.play(errorBeep!!, 1f, 1f, 0, 0, 1f)
            }
        } else {
            soundPool.play(errorBeep!!, 1f, 1f, 0, 0, 1f)
        }
    }
}