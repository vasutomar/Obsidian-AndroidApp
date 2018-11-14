package vasu.lenovo.obsidianandroidapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import android.provider.MediaStore
import android.widget.TextView
import android.widget.Toast
import java.util.concurrent.TimeUnit


class DecodeActivity : AppCompatActivity() {

    private var REQUEST_SELECT_IMAGE_IN_ALBUM = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decode)

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == resultCode && data != null && data.getData() != null) {
            val uri = data?.data
            val Img = findViewById<ImageView>(R.id.DecodeImage)
            Picasso.get().load(uri).into(Img)
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            val out = findViewById<TextView>(R.id.DecodedMessage)
            val post = PostImageForDecoding(bitmap,applicationContext,out)
            Toast.makeText(this,"Image sent. Please wait.",Toast.LENGTH_LONG).show()

        }
    }
}
