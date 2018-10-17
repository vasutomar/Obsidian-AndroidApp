package vasu.lenovo.obsidianandroidapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun StartSend(view : View) {
        val intent = Intent(this,SendActivity::class.java)
        startActivity(intent)
    }

    fun StartDecode(view : View) {
        val intent = Intent(this,DecodeActivity::class.java)
        startActivity(intent)
    }

    fun StartAbout(view : View) {
        val intent = Intent(this,AboutApp::class.java)
        startActivity(intent)
    }
}
