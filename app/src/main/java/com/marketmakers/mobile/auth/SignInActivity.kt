package com.marketmakers.mobile.auth

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.marketmakers.mobile.R
import com.google.firebase.auth.FirebaseAuth
import com.marketmakers.mobile.promotions.PromotionsActivity
import java.util.*


class SignInActivity : AppCompatActivity() {

    private val rcSignIn = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val providers = Arrays.asList(
                AuthUI.IdpConfig.GoogleBuilder().build(),
                AuthUI.IdpConfig.EmailBuilder().build()
        )

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                rcSignIn)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == rcSignIn) {
            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser ?: return

                val intent = Intent(applicationContext, PromotionsActivity::class.java)
                intent.putExtra(PromotionsActivity.EXTRA_CURRENT_USER, user.uid)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this,  "Ops, algo deu errado!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}
