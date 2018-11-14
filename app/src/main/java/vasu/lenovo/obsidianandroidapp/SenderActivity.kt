package vasu.lenovo.obsidianandroidapp
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.provider.MediaStore
import android.util.Log
import android.view.View
import com.squareup.picasso.Picasso
import okhttp3.*
import java.net.HttpURLConnection
import android.os.Environment.DIRECTORY_PICTURES
import android.support.v4.app.FragmentActivity
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*
import java.io.*


class SenderActivity() : AppCompatActivity() {

    var imageFilePath: String? = "Obs"

    //private val mSocket = IO.socket("http://192.168.43.22:5000/")
    private lateinit var Img: ImageView
    private lateinit var fbitmap : Bitmap
    private var REQUEST_SELECT_IMAGE_IN_ALBUM = 0
    private var REQUEST_CAPTURE_IMAGE = 1
    private var photoFile: File? = null

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

            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            fbitmap = bitmap
        }
        else if(requestCode == REQUEST_CAPTURE_IMAGE){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            fbitmap = imageBitmap
            Img = findViewById<ImageView>(R.id.SendingImage)
            Img.setImageBitmap(imageBitmap)
        }
    }
    fun SendResources(view: View) {
        var userMessage = findViewById<EditText>(R.id.UserMessage)
        val rg = findViewById<RadioGroup>(R.id.Radiogrp)
        val selected = findViewById<RadioButton>(rg.checkedRadioButtonId)
        val post = PostMessage(userMessage.text.toString(),fbitmap,selected.text.toString())
        Toast.makeText(this,"Message and image sent.",Toast.LENGTH_LONG).show()
    }
}
