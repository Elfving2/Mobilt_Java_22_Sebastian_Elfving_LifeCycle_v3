package com.example.lifecyclekotlin.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.lifecyclekotlin.Model.User

class DatabaseConnection(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "lifecycleDatabase"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "user"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRST_NAME = "firstName"
        private const val COLUMN_LAST_NAME = "lastName"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_PHONE_NUMBER = "phoneNumber"
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val query = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_FIRST_NAME TEXT NOT NULL, "
                + "$COLUMN_LAST_NAME TEXT NOT NULL, "
                + "$COLUMN_EMAIL TEXT NOT NULL, "
                + "$COLUMN_PASSWORD TEXT NOT NULL, "
                + "$COLUMN_PHONE_NUMBER TEXT NOT NULL)")
        sqLiteDatabase.execSQL(query)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(sqLiteDatabase)
    }

    fun createUser(firstName: String, lastName: String, email: String, password: String, phoneNumber: String) {
        val database = writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_FIRST_NAME, firstName)
        cv.put(COLUMN_LAST_NAME, lastName)
        cv.put(COLUMN_EMAIL, email)
        cv.put(COLUMN_PASSWORD, password)
        cv.put(COLUMN_PHONE_NUMBER, phoneNumber)

        val result = database.insert(TABLE_NAME, null, cv)

//        if (result == -1L) {
//            Log.d("failed", context.toString())
//        } else {
//            Log.d("Success", context.toString())
//        }
    }

    @SuppressLint("Range")
    fun logIn(email: String, password: String): User? {
        val user = User()
        try {
            val db = readableDatabase
            val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"
            val projection = arrayOf(email, password)
            val cursor = db.rawQuery(query, projection)
            Log.d("hej", cursor.count.toString())

            if (cursor.count == 1 && cursor.moveToFirst()) {
                val userFirstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME))
                val userLastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME))
                val userEmail = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
                val userPassword = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
                val userPhoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER))

                Log.d("firstName: ", userFirstName)
                Log.d("lastName: ", userLastName)
                Log.d("email: ", userEmail)
                Log.d("password: ", userPassword)
                Log.d("phoneNumber: ", userPhoneNumber)

                user.setFirstName(userFirstName)
                user.setLastName(userLastName)
                user.setEmail(userEmail)
                user.setPassword(userPassword)
                user.setPhoneNumber(userPhoneNumber)

                return user
            }
            cursor.close()
        } catch (e: IndexOutOfBoundsException) {
            Log.d("error", e.message.toString())
        }
        return null
    }
}
