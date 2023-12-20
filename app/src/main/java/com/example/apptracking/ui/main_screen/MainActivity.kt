package com.example.apptracking.ui.main_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apptracking.R
import com.example.apptracking.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.rootContainer, LoginFragment())
            .commit()
    }
}