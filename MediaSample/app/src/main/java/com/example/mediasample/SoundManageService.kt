package com.example.mediasample

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import java.io.IOException

class SoundManageService : Service() {

    private var player: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val mediaFileUriStr = "android.resource://" + packageName + "/" + R.raw.karuizawa_birds
        val medeaFileUri = Uri.parse(mediaFileUriStr)
        try {
            player?.setDataSource(this, medeaFileUri)
            player?.setOnPreparedListener(PlayerPreparedListener())
            player?.setOnCompletionListener(PlayerCompletionListener())
            player?.prepareAsync()
        } catch (ex: IOException) {
            ex.stackTrace
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if (player?.isPlaying == true) {
            player?.stop()
        }
        player?.release()
        player = null
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    private inner class PlayerPreparedListener: MediaPlayer.OnPreparedListener {
        override fun onPrepared(mp: MediaPlayer?) {
            mp?.start()
        }

    }

    private inner class PlayerCompletionListener: MediaPlayer.OnCompletionListener {
        override fun onCompletion(mp: MediaPlayer?) {
            stopSelf()
        }

    }
}
