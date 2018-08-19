package com.marketmakers.mobile.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.PointF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import com.marketmakers.mobile.R
import kotlinx.android.synthetic.main.activity_scanner.*

class ScannerActivity : AppCompatActivity(), QRCodeReaderView.OnQRCodeReadListener {

    private val permissionCamera = 321

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        with (qrdecoderview) {
            setOnQRCodeReadListener(this@ScannerActivity)
            setAutofocusInterval(2000L)
            setTorchEnabled(true)
            setBackCamera()
        }

    }

    override fun onResume() {
        super.onResume()
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    arrayOf<String>(Manifest.permission.CAMERA),
                    permissionCamera)
        } else {
            qrdecoderview.startCamera()
        }
    }

    override fun onPause() {
        super.onPause()
        qrdecoderview.stopCamera()
    }

    override fun onQRCodeRead(text: String?, points: Array<out PointF>?) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}
