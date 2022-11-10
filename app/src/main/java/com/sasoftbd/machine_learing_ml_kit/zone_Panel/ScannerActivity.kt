package com.sasoftbd.machine_learing_ml_kit.zone_Panel

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import me.dm7.barcodescanner.zxing.ZXingScannerView
import android.os.Bundle
import android.view.WindowManager
import android.content.Intent
import android.hardware.Camera
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.zxing.Result
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.PermissionListener
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.sasoftbd.machine_learing_ml_kit.R

class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private var scannerView: ZXingScannerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // hide status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_scanner)
        scannerView = findViewById(R.id.scanner_view)
        checkPermissions()
        val intent = Intent()
        intent.getStringExtra("Barcode_activity")
    }

    override fun onStart() {
        super.onStart()
        startCamera()
    }

    override fun onStop() {
        super.onStop()
        scannerView!!.stopCamera()
    }

    override fun handleResult(result: Result) {

        Toast.makeText(this, "Code = $result", Toast.LENGTH_SHORT).show()

        if (result.text != null && !result.text.isEmpty()) {

            try {
                if (intent.getStringExtra("Barcode_activity") == "brcode") {
                    Toast.makeText(
                        applicationContext,
                        "SCANNED_BR_CODE: " + result.text,
                        Toast.LENGTH_SHORT
                    ).show()
//                    val intent1 =
//                        Intent(this@ScannerActivity, TuitionFeeCollectionActivity::class.java)
//                    intent1.putExtra("br_code_id", result.text)
//                    startActivity(intent1)
//                    Log.d("SCANNER-RESULT", "" + result.text)
                } else {
//                    Toast.makeText(
//                        applicationContext,
//                        "SCANNED_DATA: " + result.text,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    val intent2 = Intent(this@ScannerActivity, QRdetailsActivity::class.java)
//                    intent2.putExtra("student_id", result.text)
//                    startActivity(intent2)
//                    Log.d("SCANNER-RESULT", "" + result.text)
                }
            } catch (e: Exception) {
//                Toast.makeText(
//                    applicationContext,
//                    "SCANNED_DATA: " + result.text,
//                    Toast.LENGTH_SHORT
//                ).show()
//                val intent2 = Intent(this@ScannerActivity, QRdetailsActivity::class.java)
//                intent2.putExtra("student_id", result.text)
//                startActivity(intent2)
                Log.d("SCANNER-RESULT", "" + result.text)
            }
        } else Toast.makeText(
            applicationContext,
            "Scanning failed! , try again",
            Toast.LENGTH_SHORT
        ).show()

        //Custom delay to remove previous data and reset camera.
        Handler().postDelayed({ startCamera() }, 2000)
    }

    private fun checkPermissions() {
        Dexter.withContext(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(permissionGrantedResponse: PermissionGrantedResponse) {
                    startCamera()
                }

                override fun onPermissionDenied(permissionDeniedResponse: PermissionDeniedResponse) {
                    Toast.makeText(
                        this@ScannerActivity,
                        "You must accept this permission",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissionRequest: PermissionRequest,
                    permissionToken: PermissionToken
                ) {
                }
            }).check()
    }

    fun startCamera() {
        scannerView!!.setResultHandler(this@ScannerActivity)
        scannerView!!.startCamera(Camera.CameraInfo.CAMERA_FACING_BACK) //SET Camera id 1  for front camera
        scannerView!!.setAutoFocus(true)
    }
}