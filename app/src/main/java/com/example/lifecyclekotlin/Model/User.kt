package com.example.lifecyclekotlin.Model

import java.io.Serializable

class User : Serializable {
    private var firstName: String? = null
    private var lastName: String? = null
    private var email: String? = null
    private var password: String? = null
    private var phoneNumber: String? = null

    fun User(
        firstName: String?,
        lastName: String,
        email: String,
        password: String,
        phoneNumber: String
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
        this.phoneNumber = phoneNumber
    }

    fun User() {}

    fun getFirstName(): String? {
        return firstName
    }

    fun setFirstName(firstName: String?) {
        this.firstName = firstName
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String) {
        this.lastName = lastName
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: String) {
        this.phoneNumber = phoneNumber
    }
}