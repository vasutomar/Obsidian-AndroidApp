package vasu.lenovo.obsidianandroidapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainIcon = findViewById<ImageView>(R.id.MainIcon)
        mainIcon.setImageResource(R.drawable.icon)
    }

    fun StartUserPanel(view: View) {
        val intent = Intent(this,UserPanel::class.java)
        startActivity(intent)
    }

    fun StartAboutApplication(view : View) {
        val intent = Intent(this,AboutApp::class.java)
        startActivity(intent)
    }

    fun ExitApplication(view: View) {
        finish()
    }
}
