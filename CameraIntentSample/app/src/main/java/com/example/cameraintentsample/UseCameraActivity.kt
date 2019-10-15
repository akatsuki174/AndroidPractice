package com.example.cameraintentsample

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView

class UseCameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_camera)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            val bitmap = data?.getParcelableExtra<Bitmap>("data")
            val ivCamera = findViewById<ImageView>(R.id.ivCamera)
            ivCamera.setImageBitmap(bitmap)
        }
    }

    fun onCameraImageClick(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 200)
    }

}
