package com.example.morningintents

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
    lateinit var mSms:Button
    lateinit var mEmail:Button
    lateinit var mCamera:Button
    lateinit var mShare:Button
    lateinit var mMpesa:Button
    lateinit var mCall:Button
    lateinit var mWebsite:Button
    lateinit var btnMap:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSms = findViewById(R.id.mBtSms)
        mEmail = findViewById(R.id.mBtEmail)
        mCamera = findViewById(R.id.mBtCamera)
        mShare = findViewById(R.id.mBtShare)
        mMpesa = findViewById(R.id.mBtMpesa)
        mCall = findViewById(R.id.mBtCall)
        mWebsite = findViewById(R.id.mBtWebsite)
        btnMap = findViewById(R.id.mBtnMap)

        //set onclick listeners
        mSms.setOnClickListener{
            val uri: Uri = Uri.parse("smsto:0728824044")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "The SMS text")
            startActivity(intent)


    }
        mEmail.setOnClickListener{
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "abc@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Don is the greatest")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "This Email is Weird")
            startActivity(Intent.createChooser(emailIntent, "He can now make Android apps"))

        }
        mShare.setOnClickListener{
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!")
            startActivity(shareIntent)


        }
        mMpesa.setOnClickListener{
            val simToolKitLaunchIntent =
                applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { startActivity(it) }

        }
        mCall.setOnClickListener{
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+918511812660"))
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
            val tembea = Intent(this@MainActivity, websiteActivity::class.java)
            startActivity(tembea)

        }
        mCamera.setOnClickListener{
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 1)

        }
        btnMap.setOnClickListener{

        }
}
}