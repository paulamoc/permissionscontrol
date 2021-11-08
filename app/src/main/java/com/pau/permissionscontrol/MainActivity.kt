package com.pau.permissionscontrol

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPhoto.setOnClickListener { checkPermissions() }

    }

    private fun checkPermissions() {
      if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=PackageManager.PERMISSION_GRANTED){
          //permiso no aceptadp por el momento
          requestCameraPermission()
      }else{
         openCamera()
       }
    }

    private fun openCamera() {
        Toast.makeText(this, "Abriendo cámara", Toast.LENGTH_SHORT).show()
    }

    private fun requestCameraPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
            //El usuario ya ha rechazado los permisos
            Toast.makeText(this, "Permisos rechazados", Toast.LENGTH_SHORT).show()
        }else{
            //perdir permiso
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 777)
    }
}

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 777){ //Nuestros permisos
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera()
            }else{
                Toast.makeText(this, "Permisos rechazados por primera vez", Toast.LENGTH_SHORT).show()
    }}
    }
}