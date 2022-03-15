package com.example.mygithubuserapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubuserapp.databinding.ItemRowUserBinding

class ListUserAdapter(private val listUser: ArrayList<User>) :
    RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private lateinit var binding: ItemRowUserBinding
    private lateinit var onItemClickFeedback: OnItemClickFeedback

    interface OnItemClickFeedback {
        fun onItemClicked(data: User)
    }

    fun setOnItemClickFeedback(onItemClickFeedback: OnItemClickFeedback) {
        this.onItemClickFeedback = onItemClickFeedback
    }

    class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        binding =
            ItemRowUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (username, _, company, _, followers, following, _, photo) = listUser[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .circleCrop()
            .into(holder.binding.imgItemPhoto)
        holder.binding.apply {
            tvItemUsername.text = username
            tvItemCompany.text = "Company: $company"
            tvItemFollowers.text = "Followers: $followers"
            tvItemFollowing.text = "Following: $following"
        }

        holder.itemView.setOnClickListener {
            onItemClickFeedback.onItemClicked(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listUser.size
}