package com.muthoknee.morningintents

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    lateinit var mSms: Button
    lateinit var mEmail: Button
    lateinit var mCamera: Button
    lateinit var mShare: Button
    lateinit var mMpesa: Button
    lateinit var mCall: Button
    lateinit var mWebsite: Button
    lateinit var mMap: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSms=findViewById(R.id.mBtnsms)
        mEmail=findViewById(R.id.mBtnEmail)
        mCamera=findViewById(R.id.mBtnCamera)
        mShare=findViewById(R.id.mBtnShare)
        mMpesa=findViewById(R.id.mBtnMpesa)
        mCall=findViewById(R.id.mBtnCall)
        mWebsite=findViewById(R.id.mBtnWebsite)
        mMap=findViewById(R.id.MBtnMap)
        // set on click
        mSms.setOnClickListener{
            val uri: Uri = Uri.parse("smsto:0712345678")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "The SMS text")
            startActivity(intent)
        }
        mEmail.setOnClickListener{
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "mish@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
        mCamera.setOnClickListener{
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 1)
        }
        mShare.setOnClickListener{
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app from https://www.mish.com!")
            startActivity(shareIntent)
        }
        mMpesa.setOnClickListener{
            val simToolKitLaunchIntent =
                applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { startActivity(it) }
        }
        mCall.setOnClickListener{
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0712345678"))
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf<String>(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }
        }
        mWebsite.setOnClickListener{
        val tembea = Intent(this@MainActivity, WebsiteActivity::class.java)
            startActivity(tembea)
        }
        mMap.setOnClickListener {
            val ramani = Intent(this, MapsActivity::class.java)
            startActivity(ramani)
        }
    }
}