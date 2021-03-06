package vasu.lenovo.obsidianandroidapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View

class MainActivity : AppCompatActivity() {

    private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.INTERNET)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askRequiredPermissions()
    }

    private fun arePermissionGranted(): Boolean {
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return true
    }

    private fun askRequiredPermissions() {
        if (!arePermissionGranted()) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_REQUIRED, 101)
        }
    }

    fun StartAbout(view : View) {
        val intent = Intent(this,AboutApp::class.java)
        startActivity(intent)
    }
    fun CaptureCamera(view : View) {
        val intent = Intent(this,SenderActivity()::class.java)
        intent.putExtra("Code",1)
        startActivity(intent)
    }
    fun ChoosePicture(view : View) {
        val intent = Intent(this,SenderActivity()::class.java)
        intent.putExtra("Code",2)
        startActivity(intent)
    }
    fun DecodeImage(view : View) {
        val intent = Intent(this,DecodeActivity::class.java)
        startActivity(intent)
    }
}
