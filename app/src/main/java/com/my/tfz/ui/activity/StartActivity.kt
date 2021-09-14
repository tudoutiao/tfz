package com.my.tfz.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.my.tfz.MainActivity
import com.my.tfz.R
import java.util.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val mHandler = Handler(Looper.getMainLooper())
        mHandler.postDelayed({
            startActivity(Intent(this@StartActivity, MainActivity::class.java))
            finish()
            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out)
        }, 1000)

    }
}