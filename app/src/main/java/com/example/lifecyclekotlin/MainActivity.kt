package com.example.lifecyclekotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lifecyclekotlin.Activities.LoggedIn
import com.example.lifecyclekotlin.Database.DatabaseConnection
import com.example.lifecyclekotlin.fragments.RegisterFragment

class MainActivity : AppCompatActivity() {
    private var isHidden = true
    private lateinit var frame: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login = findViewById<Button>(R.id.login)
        val register = findViewById<Button>(R.id.register)
        frame = findViewById(R.id.frameLayout)
        val loginEmail = findViewById<EditText>(R.id.loginEmail)
        val loginPassword = findViewById<EditText>(R.id.loginPassword)

        login.setOnClickListener {
            DatabaseConnection(this).use { database ->
                val email = loginEmail.text.toString().trim()
                val password = loginPassword.text.toString().trim()
                val userObject = database.logIn(email, password)
                if (userObject != null) {
                    Log.d("success", "success")
                    val intent = Intent(this, LoggedIn::class.java)
                    intent.putExtra("userObject", userObject)
                    startActivity(intent)

                    // reset after login in
                    loginEmail.setText("")
                    loginPassword.setText("")
                } else {
                    //Toast.makeText()
                    Log.d("failed", "Login failed")
                }
            }
        }

        register.setOnClickListener { replaceFragment(RegisterFragment()) }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (isHidden) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, fragment)
            fragmentTransaction.commit()
            frame.visibility = View.VISIBLE
            isHidden = false
        } else {
            frame.visibility = View.INVISIBLE
            isHidden = true
        }
    }
}
