package vasu.lenovo.obsidianandroidapp
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

class SenderActivity() : AppCompatActivity() {

    private val mSocket = IO.socket("http://chat.socket.io")
    private var REQUEST_SELECT_IMAGE_IN_ALBUM = 0
    private var REQUEST_CAPTURE_IMAGE = 1
    private val message = String

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
            val Img = findViewById<ImageView>(R.id.SendingImage)
            Picasso.get().load(uri).into(Img)
        }
        else {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val Img = findViewById<ImageView>(R.id.SendingImage)
            Img.setImageBitmap(imageBitmap)
        }
    }
    fun SendResources(view: View) {
        print("here")
        val user_message = findViewById(R.id.UserMessage) as EditText
        Toast.makeText(this, user_message.text, Toast.LENGTH_SHORT).show()
        mSocket.connect()
        mSocket.emit("new message",user_message.text)
    }
}
