package com.strangersplay.user_profile.display.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.strangersplay.R
import com.strangersplay.user_profile.display.model.Comment
import kotlinx.android.synthetic.main.user_comments_item.view.*

class UserProfileAdapter : RecyclerView.Adapter<UserProfileAdapter.CommentsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_comments_item, parent, false)
        return CommentsHolder(inflatedView)
    }

    val commentsList = mutableListOf<Comment>()

    fun addList(listToAdd: List<Comment>) {
        if (commentsList.isNotEmpty())
            commentsList.clear()
        commentsList.addAll(listToAdd)
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }

    override fun onBindViewHolder(holder: CommentsHolder, position: Int) {
        val item = commentsList[position]
        holder.bind(item)
    }

    class CommentsHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(comment: Comment) {
            view.commentUsername.text = comment.author
            view.commentContent.text = comment.commentText
        }
    }

}