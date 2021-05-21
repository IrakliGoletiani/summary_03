package com.example.sammary3

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val editUser = intent.getParcelableExtra<User>("user")
        val position = intent.getIntExtra("position", -1)

        val etFirstName = findViewById<EditText>(R.id.etFirstName)
        val etLastName = findViewById<EditText>(R.id.etLastName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val btnSave = findViewById<Button>(R.id.btnSave)

        etFirstName.setText(editUser?.firstName)
        etLastName.setText(editUser?.lastName)
        etEmail.setText(editUser?.email)

        btnSave.setOnClickListener {
            val user = User(
                etFirstName.text.toString(),
                etLastName.text.toString(),
                etEmail.text.toString()
            )

            intent.putExtra("editUser", user)
            intent.putExtra("editPosition", position)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}