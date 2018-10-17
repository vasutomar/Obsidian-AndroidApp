package vasu.lenovo.obsidianandroidapp

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso

class SenderActivity() : AppCompatActivity() {

    private var REQUEST_SELECT_IMAGE_IN_ALBUM = 0
    private var REQUEST_CAPTURE_IMAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sender)

        val intent = getIntent()
        val code = intent.getIntExtra("Code",1)

        if (code == 1) {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                //Log.e("I am","Capturing")
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, 1)
                }
            }
        }

        else {
            //Log.e("I am","Picking")
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, 0)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM) {
            val uri = data?.data
            val Img = findViewById<ImageView>(R.id.SendingImage)
            Picasso.get().load(uri).into(Img)
        }
        else {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val Img = findViewById<ImageView>(R.id.SendingImage)
            Img.setImageBitmap(imageBitmap)
        }
    }
}
