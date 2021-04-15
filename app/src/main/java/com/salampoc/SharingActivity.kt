package com.salampoc

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.facebook.share.widget.ShareDialog


class SharingActivity : AppCompatActivity() {

    private lateinit var btn_share_fb: Button
    private lateinit var btn_general_share: Button

    // private lateinit var callbackManager: CallbackManager
    private lateinit var shareDialog: ShareDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sharing)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Share"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btn_share_fb = findViewById(R.id.btn_share_fb)
        btn_general_share = findViewById(R.id.btn_general_share)
        //Init FB

        // callbackManager = CallbackManager.Factory.create()

        //shareItemWithFacebookSDK()

        btn_general_share.setOnClickListener{
            generalShare("SALAM PRODUCT","hurrayy","https://demohosting-280a8.web.app/")
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //callbackManager.onActivityResult(requestCode, resultCode, data)
    }


    private fun generalShare(title: String?, text: String?, url: String?) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"

        intent.putExtra(Intent.EXTRA_SUBJECT, title)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        intent.putExtra(Intent.EXTRA_TEXT, url)
        startActivity(Intent.createChooser(intent, "Share Using"))
    }

//    private fun shareItemWithFacebookSDK() {
//        shareDialog = ShareDialog(this)
//
//        btn_share_fb.setOnClickListener {
//
//            shareDialog.registerCallback(callbackManager, object : FacebookCallback<Sharer.Result> {
//                override fun onSuccess(result: Sharer.Result?) {
//                    Toast.makeText(this@SharingActivity, "success", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onCancel() {
//                    Toast.makeText(this@SharingActivity, "cancel", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onError(error: FacebookException?) {
//                    error?.printStackTrace()
//                    Toast.makeText(
//                        this@SharingActivity,
//                        "Error :" + error?.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//            })
//            var linkContent = ShareLinkContent.Builder()
//                .setQuote("Have a look at this product")
//                .setContentUrl(Uri.parse("https://www.salams.com/cjab94q41-cjab94q41-girl-dress-la-stupenderia"))
//                .build()
//
//            if (ShareDialog.canShow(ShareLinkContent::class.java)) {
//                shareDialog.show(linkContent)
//            }
//        }
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)

    }


}