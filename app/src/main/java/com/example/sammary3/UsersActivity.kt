package com.example.sammary3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UsersActivity : AppCompatActivity() {

    private val users = mutableListOf<User>()

    lateinit var adapter: UserAdapter

    companion object {
        const val CODE1 = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        setData()
        init()
    }

    private fun setData() {
        repeat(4) {
            users.add(User("Lionel", "Messi", "lionel.messi@gmail.com"))
            users.add(User("Cristiano", "Ronaldo", "cristiano.ronaldo@gmail.com"))
            users.add(User("Harry", "Kane", "harry.kane@gmail.com"))
        }
    }

    private fun init() {
        adapter = UserAdapter(users, object : UserItemListener {
            override fun updateUser(position: Int) {
                val intent = Intent(this@UsersActivity, EditUserActivity::class.java)
                intent.putExtra("user", users[position])
                intent.putExtra("position", position)
                startActivityForResult(intent, CODE1)
            }

            override fun deleteUser(position: Int) {

                adapter.notifyItemRemoved(position)
            }
        })

        val usersRecyclerView = findViewById<RecyclerView>(R.id.usersRecyclerView)

        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        usersRecyclerView.adapter = adapter

        val addUserButton = findViewById<Button>(R.id.btnAdd)
        addUserButton.setOnClickListener {
            users.add(User("Awesome", "Person", "awesome.person@gmail.com"))
            adapter.notifyItemInserted(users.size - 1)
            usersRecyclerView.scrollToPosition(users.size - 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE1 && resultCode == Activity.RESULT_OK && data != null) {
            val editedUser = data.getParcelableExtra<User>("editUser")
            val position = data.getIntExtra("Position", -1)

            if (editedUser != null) {

                adapter.notifyItemChanged(position)
            }
        }
    }
}