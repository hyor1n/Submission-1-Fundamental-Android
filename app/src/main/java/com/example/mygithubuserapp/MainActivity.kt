package com.example.mygithubuserapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<User>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvUsers.setHasFixedSize(true)

        list.addAll(listUsers)
        Log.d("MainActivity", list.toString())
        showRecyclerList()
    }

    private val listUsers: ArrayList<User>
        @SuppressLint("Recycle")
        get() {
            val username = resources.getStringArray(R.array.username)
            val name = resources.getStringArray(R.array.name)
            val company = resources.getStringArray(R.array.company)
            val location = resources.getStringArray(R.array.location)
            val followers = resources.getStringArray(R.array.followers)
            val following = resources.getStringArray(R.array.following)
            val repo = resources.getStringArray(R.array.repository)
            val photo = resources.obtainTypedArray(R.array.avatar)

            val listUser = ArrayList<User>()
            for (i in username.indices) {
                val user = User(
                    username[i],
                    name[i],
                    company[i],
                    location[i],
                    followers[i],
                    following[i],
                    repo[i],
                    photo.getResourceId(i, -1)
                )
                listUser.add(user)
            }
            return listUser
        }

    private fun showRecyclerList() {
        val listUserAdapter = ListUserAdapter(list)
        binding.rvUsers.adapter = listUserAdapter
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvUsers.layoutManager = GridLayoutManager(this, 2)
            listUserAdapter.setOnItemClickFeedback(object : ListUserAdapter.OnItemClickFeedback {
                override fun onItemClicked(data: User) {
                    val setIntent = Intent(this@MainActivity, DetailUserActivity::class.java)
                    setIntent.putExtra(DetailUserActivity.EXTRA, data)
                    startActivity(setIntent)
                }
            })
        } else {
            binding.rvUsers.layoutManager = LinearLayoutManager(this)
            listUserAdapter.setOnItemClickFeedback(object : ListUserAdapter.OnItemClickFeedback {
                override fun onItemClicked(data: User) {
                    val setIntent = Intent(this@MainActivity, DetailUserActivity::class.java)
                    setIntent.putExtra(DetailUserActivity.EXTRA, data)
                    startActivity(setIntent)
                }
            })
        }
    }
}