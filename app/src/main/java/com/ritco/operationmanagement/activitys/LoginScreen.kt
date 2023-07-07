package com.ritco.operationmanagement.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ritco.om.R

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
    }

    fun next(v:View)
    {
        var intent= Intent(this,ThcGenerat::class.java)
        startActivity(intent)
    }
}