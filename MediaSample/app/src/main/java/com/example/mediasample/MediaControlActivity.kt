package com.example.mediasample

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import java.io.IOException
import java.net.URI

class MediaControlActivity : AppCompatActivity() {

    private var player: MediaPlayer? = null
    private var btPlay: Button? = null
    private var btBack: Button? = null
    private var btForward: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_control)

        btPlay = findViewById(R.id.btPlay)
        btBack = findViewById(R.id.btBack)
        btForward = findViewById(R.id.btForward)

        player = MediaPlayer()
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

        val loopSwitch = findViewById<Switch>(R.id.swLoop)
        loopSwitch.setOnCheckedChangeListener(LoopSwitchChangedListener())
    }

    override fun onDestroy() {
        super.onDestroy()
        if (player?.isPlaying == true) {
            player?.stop()
        }
        player?.release()
        player = null
    }

    fun onPlayButtonClick(view: View) {
        if (player?.isPlaying == true) {
            player?.pause()
            btPlay?.setText(R.string.bt_play_play)
        } else {
            player?.start()
            btPlay?.setText(R.string.bt_play_pause)
        }
    }

    fun onBackButtonClick(view: View) {
        player?.seekTo(0)
    }

    fun onForwardButtonClick(view: View) {
        val duration = player?.duration
        player?.seekTo(duration ?: 0)
        if (player?.isPlaying == false) {
            player?.start()
        }
    }

    private inner class PlayerPreparedListener: MediaPlayer.OnPreparedListener {
        override fun onPrepared(mp: MediaPlayer?) {
            btPlay?.isEnabled = true
            btBack?.isEnabled = true
            btForward?.isEnabled = true
        }

    }

    private inner class PlayerCompletionListener: MediaPlayer.OnCompletionListener {
        override fun onCompletion(mp: MediaPlayer?) {
            if (player?.isLooping == false) {
                btPlay?.setText(R.string.bt_play_play)
            }
        }

    }

    private inner class LoopSwitchChangedListener: CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            player?.isLooping = isChecked
        }
    }
}
