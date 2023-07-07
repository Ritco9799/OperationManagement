package com.ritco.operationmanagement.ktApplication


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.ritco.om.R
import com.ritco.om.databinding.ActivityBarScannerBinding
import java.io.IOException


class BarScanner : AppCompatActivity() {
    lateinit var binding: ActivityBarScannerBinding


    private var barcodeDetector: BarcodeDetector? = null
    private var cameraSource: CameraSource? = null
    private val REQUEST_CAMERA_PERMISSION = 201
    private var barcodeData: String? = null
    //This class provides methods to play DTMF tones
    private var toneGen1: ToneGenerator? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_bar_scanner)
        val view = binding.root
        setContentView(view)
        toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        initialiseDetectorsAndSources();





    }

    fun initialiseDetectorsAndSources()
    {
        //Toast.makeText(getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();

        //Toast.makeText(getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();
        barcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()

        cameraSource = CameraSource.Builder(this, barcodeDetector)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true) //you should add this feature
            .build()

        binding.surfaceView!!.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(
                            this@BarScanner,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        cameraSource?.start( binding.surfaceView!!.holder)
                    } else {
                        ActivityCompat.requestPermissions(
                            this@BarScanner,
                            arrayOf<String>(Manifest.permission.CAMERA),
                            REQUEST_CAMERA_PERMISSION
                        )
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource?.stop()
            }
        })


        barcodeDetector?.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
                // Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            override fun receiveDetections(detections: Detections<Barcode>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() != 0) {
                    binding.barcodeText!!.post {
                        if (barcodes.valueAt(0).email != null) {
                            binding.barcodeText.removeCallbacks(null)
                            barcodeData = barcodes.valueAt(0).email.address
                            binding.barcodeText.text = barcodeData
                            sendData(barcodeData.toString())
                            toneGen1!!.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
                        } else {
                            barcodeData = barcodes.valueAt(0).displayValue
                            binding.barcodeText.text = barcodeData
                            sendData(barcodeData.toString())
                            toneGen1!!.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
                        }
                    }
                }
            }
        })
    }
fun sendData(data:String)
{
    val intent = Intent()
    intent.putExtra("barcode", data)
    setResult(Activity.RESULT_OK, intent)
    finish()
}
    override fun onResume() {
        super.onResume()
        getSupportActionBar()?.hide();
        initialiseDetectorsAndSources();
    }

    override fun onPause() {
        super.onPause()
        getSupportActionBar()?.hide();
        cameraSource?.release();

    }


}