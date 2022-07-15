package com.safaorhan.events.versus.events.sound

import android.view.View
import com.safaorhan.events.EventHandler
import com.safaorhan.events.versus.sound.SoundManager
import javax.inject.Inject

class SoundEventHandler @Inject constructor(
    private val soundManager: SoundManager
) : EventHandler<SoundEvent> {
    override fun handle(view: View, event: SoundEvent) {
        soundManager.playErrorBeep()
    }
}