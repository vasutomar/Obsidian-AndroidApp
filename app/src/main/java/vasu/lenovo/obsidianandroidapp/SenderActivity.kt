package vasu.lenovo.obsidianandroidapp
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.io.IOException


class SenderActivity() : AppCompatActivity() {

    //private val mSocket = IO.socket("http://192.168.43.22:5000/")
    private lateinit var Img: ImageView
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
                    startActivityForResult(takePictureIntent, REQUEST_CAPTURE_IMAGE)
                }
            }
        }

        else {
            //Log.e("I am","Picking")
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM) {
            val uri = data?.data
            Img = findViewById<ImageView>(R.id.SendingImage)
            Picasso.get().load(uri).into(Img)

            /*var bitmap : Bitmap? = null
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri)
                var bos = ByteArrayOutputStream()
                if (bitmap != null)
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos)
                var b = bos.toByteArray()
                encodedImage = String(b, Charset.defaultCharset())

                try {
                    obj.put("image",encodedImage)
                } catch(e: JSONException) {
                    e.printStackTrace()
                }

                mSocket.emit("image",b)
            } catch(e: IOException) {
                e.printStackTrace()
            }*/
        }
        else if(requestCode == REQUEST_CAPTURE_IMAGE){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            Img = findViewById<ImageView>(R.id.SendingImage)
            Img.setImageBitmap(imageBitmap)
        }
    }
    fun SendResources(view: View) {

        var userMessage = findViewById<EditText>(R.id.UserMessage)
        val post = PostMessage(userMessage.text.toString(),Img)
        Log.e("message is ",userMessage.text.toString())
    }
}
