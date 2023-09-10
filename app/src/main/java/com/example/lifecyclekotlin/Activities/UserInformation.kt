package com.example.lifecyclekotlin.Activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.lifecyclekotlin.Model.User
import com.example.lifecyclekotlin.R
import com.example.lifecyclekotlin.MainActivity
import com.google.android.material.navigation.NavigationView

class UserInformation : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var userInformation: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navigationView: NavigationView = findViewById(R.id.navView)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        navigationView.bringToFront()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val intent = intent
        userInformation = intent.getSerializableExtra("userObject") as User
        navigationView.setNavigationItemSelectedListener(this)

        val firstName = userInformation.getFirstName()
        val lastName = userInformation.getLastName()
        val email = userInformation.getEmail()
        val password = userInformation.getPassword()
        val phoneNumber = userInformation.getPhoneNumber()

        val firstNameView: TextView = findViewById(R.id.firstNameView)
        val lastNameView: TextView = findViewById(R.id.lastNameView)
        val emailView: TextView = findViewById(R.id.emailView)
        val passwordView: TextView = findViewById(R.id.passwordView)
        val phoneNumberView: TextView = findViewById(R.id.phoneNumberView)

        firstNameView.text = "First Name: $firstName"
        lastNameView.text = "Last Name: $lastName"
        emailView.text = "Email: $email"
        passwordView.text = "Password: $password"
        phoneNumberView.text = "Phone Number: $phoneNumber"
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logOut -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.home -> {
                val intent = Intent(this, LoggedIn::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}
