package vasu.lenovo.obsidianandroidapp

import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView

class SendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send)


        val mainicon = findViewById<ImageView>(R.id.MainIcon)
        mainicon.setImageResource(R.drawable.icon)

    }

    fun CaptureCamera(view: View) {

    }
    fun ChoosePicture(view: View) {

    }
}
