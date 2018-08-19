package com.marketmakers.mobile.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.marketmakers.mobile.R
import com.marketmakers.mobile.model.User
import kotlinx.android.synthetic.main.activity_dots_earned.*

class DotsEarnedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dots_earned)

        val user = intent.extras.get(extraUserUpdated) as User

        txt_dots_earned.text = user.dots.toString()

        btn_ok_dots_earned.setOnClickListener { v ->
            val intent = Intent(applicationContext, PromotionsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra(PromotionsActivity.EXTRA_CURRENT_USER, user.userApp)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        val extraUserUpdated = "userUpdated"
    }
}
