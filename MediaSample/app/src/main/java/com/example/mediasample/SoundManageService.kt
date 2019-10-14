package com.example.mediasample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import androidx.core.app.NotificationCompat
import java.io.IOException
import java.nio.channels.Channel

class SoundManageService : Service() {

    private var player: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer()

        val id = "soundmanagerservice_notification_channel"
        val name = getString(R.string.notification_channel_name)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id, name, importance)
        val manager = getSystemService((Context.NOTIFICATION_SERVICE)) as NotificationManager
        manager.createNotificationChannel(channel)
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

            val builder = NotificationCompat.Builder(
                this@SoundManageService,
                "soundmanagerservice_notification_channel"
            )
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
            builder.setContentTitle(getString(R.string.msg_notification_title_start))
            builder.setContentText(getString(R.string.msg_notification_text_start))
            val intent = Intent(this@SoundManageService, SoundStartActivity::class.java)
            intent.putExtra("fromNotification", true)
            val stopServiceIntent = PendingIntent.getActivity(this@SoundManageService, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
            builder.setContentIntent(stopServiceIntent)
            builder.setAutoCancel(true)
            val notification = builder.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(1, notification)
        }

    }

    private inner class PlayerCompletionListener: MediaPlayer.OnCompletionListener {
        override fun onCompletion(mp: MediaPlayer?) {
            val builder = NotificationCompat.Builder(
                this@SoundManageService,
                "soundmanagerservice_notification_channel"
            )
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
            builder.setContentTitle(getString(R.string.msg_notification_title_finish))
            builder.setContentText(getString(R.string.msg_notification_text_finish))
            val notification = builder.build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(0, notification)
            stopSelf()
        }

    }
}
