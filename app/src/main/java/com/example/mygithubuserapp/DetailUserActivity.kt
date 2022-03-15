package com.example.mygithubuserapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.mygithubuserapp.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    companion object {
        const val EXTRA = "extra"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getDetailUser = intent.getParcelableExtra<User>(EXTRA) as User

        Glide.with(this)
            .load(getDetailUser.photo)
            .circleCrop()
            .into(binding.imageView)
        binding.apply {
            tvName.text = getDetailUser.name
            tvUsername.text = "@" + getDetailUser.username
            tvCompany.text = getDetailUser.company
            tvLocation.text = getDetailUser.location
            resultFollowers.text = getDetailUser.followers
            resultFollowing.text = getDetailUser.following
            resultRepo.text = getDetailUser.repo
        }
    }
}