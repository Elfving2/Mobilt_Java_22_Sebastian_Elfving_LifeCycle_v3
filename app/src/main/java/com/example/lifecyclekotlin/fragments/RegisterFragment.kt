package com.example.lifecyclekotlin.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.lifecyclekotlin.R
import com.example.lifecyclekotlin.Database.DatabaseConnection

class RegisterFragment : Fragment() {

    private lateinit var view: View
    private lateinit var context: Context
    private lateinit var registerNow: Button
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var phoneNumber: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false)

        registerNow = view.findViewById(R.id.registerNow)

        firstName = view.findViewById(R.id.firstNameTextField)
        lastName = view.findViewById(R.id.lastNameTextField)
        email = view.findViewById(R.id.loginEmail)
        password = view.findViewById(R.id.passwordTextField)
        phoneNumber = view.findViewById(R.id.phoneNumberTextField)
        context = requireActivity()

        registerNow.setOnClickListener {
            // Fragment doesn't have its own context, so we use the hosting activity's context
            DatabaseConnection(context).use { dbConnection ->
                dbConnection.createUser(
                    firstName.text.toString().trim(),
                    lastName.text.toString().trim(),
                    email.text.toString().trim(),
                    password.text.toString().trim(),
                    phoneNumber.text.toString().trim()
                )
            }
        }
        return view
    }
}
