package com.salampoc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.internal.CallbackManagerImpl
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import org.json.JSONObject


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btn_google: Button
    private lateinit var btn_google_sign_out: Button

    private lateinit var btn_facebook: Button
    private lateinit var btn_fb_sign_out:Button
    private lateinit var callbackManager: CallbackManager

    //Google login
    private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        const val RC_GOOGLE_SIGN_IN = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_google = findViewById(R.id.btn_google)
        btn_facebook = findViewById(R.id.btn_facebook)
        btn_google_sign_out = findViewById(R.id.btn_google_sign_out)
        btn_fb_sign_out= findViewById(R.id.btn_fb_sign_out)
        btn_fb_sign_out.setOnClickListener(this)
        btn_google.setOnClickListener(this)
        btn_facebook.setOnClickListener(this)

        btn_google_sign_out.setOnClickListener(this)

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode() -> {
                callbackManager.onActivityResult(
                    CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode(),
                    resultCode,
                    data
                )
            }

            RC_GOOGLE_SIGN_IN -> {
                // The Task returned from this call is always completed, no need to attach
                // a listener.
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
            }
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)

            Toast.makeText(applicationContext, "google sign in success", Toast.LENGTH_SHORT).show()
            // Signed in successfully, show authenticated UI.
            if (account != null) {
                Log.d("GOOGLE", "id= " + account.id)
                Log.d("GOOGLE", "display name= " + account.displayName)
                Log.d("GOOGLE", "given name = " + account.givenName)
                Log.d("GOOGLE", "email = " + account.email)
                Log.d("GOOGLE", "Photo URI = " + account.photoUrl)
            }

            // Do rest of the code like switching activity

            val intent = Intent(this,DashboardActivity::class.java)
            startActivity(intent)
            finish()

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("GOOGLE", "error =" + e.message)
        }
    }


    /**
     * This method is responsible for the facebook login
     * Public Permission : public_profile : - id,first_name,last_name,middle_name,name,name_format,picture,short_name
     * Gender Permission : user_gender : - gender
     */
    private fun loginWithFacebook() {
        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance()
            .logInWithReadPermissions(this, mutableListOf("user_gender", "public_profile"))

        LoginManager.getInstance().registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    val accessToken = result?.accessToken
                    val request = GraphRequest.newMeRequest(
                        accessToken
                    ) { `object`, response ->
                        Toast.makeText(applicationContext, "succeed", Toast.LENGTH_SHORT)
                            .show()
                        try {
                            if (`object`?.has("id") == true) {
                                Log.d("FBDATA", `object`.getString("name"))
                                Log.d("FBDATA", `object`.getString("email"))
                                Log.d("FBDATA", `object`.getString("gender"))
                                Log.d("FBDATA", `object`.getString("birthday"))
                                Log.d(
                                    "FBDATA",
                                    JSONObject(`object`.getString("picture")).getJSONObject(
                                        "data"
                                    ).getString("url")
                                )

                            }
                        } catch (e: Exception) {
                            Log.d("FBDATA", "error" + e.message)
                        }

                        // switching the activity
                        val intent = Intent(applicationContext,DashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    val parameters = Bundle()
                    parameters.putString(
                        "fields",
                        "id,name,email,birthday,gender,picture.type(large)"
                    )
                    request.parameters = parameters
                    request.executeAsync()
                }

                override fun onCancel() {
                    Toast.makeText(applicationContext, "cancelled", Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: FacebookException?) {
                    Toast.makeText(applicationContext, "error ", Toast.LENGTH_SHORT).show()
                }

            })
    }

    /**
     *  This method send the user to onActivityResult method
     */
    private fun loginWithGoogle() {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
    }

    /**
     * If user is logged in then we can make him logout
     */
    private fun logoutWithGoogle() {
        googleSignInClient.signOut().addOnCompleteListener(this, object : OnCompleteListener<Void> {
            override fun onComplete(p0: Task<Void>) {
                Toast.makeText(applicationContext, "google logout successfully", Toast.LENGTH_SHORT).show()
            }

        })
    }

    /**
     *
     */
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_facebook -> {
                loginWithFacebook()
            }
            R.id.btn_google -> {
                loginWithGoogle()
            }
            R.id.btn_google_sign_out -> {
                logoutWithGoogle()
            }

            R.id.btn_fb_sign_out->{
                logoutWithFacebook()
            }
        }
    }

    private fun logoutWithFacebook() {
        val profile = Profile.getCurrentProfile()
        if (profile != null) {
            // user has logged in
            Log.d("FBDATA",profile.name)
            LoginManager.getInstance().logOut()
            Toast.makeText(this, "Logout successfully", Toast.LENGTH_SHORT).show()
        } else {
            // user has not logged in
            Toast.makeText(this, "not log in", Toast.LENGTH_SHORT).show()
        }

    }
}