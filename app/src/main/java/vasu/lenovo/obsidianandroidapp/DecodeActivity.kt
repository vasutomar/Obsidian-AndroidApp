package vasu.lenovo.obsidianandroidapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView

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
        when (requestCode) {
            101 -> {
                if (data != null) {
                    val uri = data.data
                    displaySelectedImage(getBitmapFromUri(uri))
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }
    private fun getBitmapFromUri(uri: Uri): Bitmap {
        val parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor = parcelFileDescriptor?.fileDescriptor
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()
        return image
    }

    private fun displaySelectedImage(imageBitmap: Bitmap) {
        val iv_selected_image = findViewById<ImageView>(R.id.DecImage)
        iv_selected_image.setImageBitmap(imageBitmap)
    }
}
