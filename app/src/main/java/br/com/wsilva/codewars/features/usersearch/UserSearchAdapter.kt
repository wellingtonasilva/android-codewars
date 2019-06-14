package br.com.wsilva.codewars.features.usersearch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.codewars.R
import br.com.wsilva.codewars.model.entity.UserEntity
import kotlinx.android.synthetic.main.lay_user_search_adapter.view.*

class UserSearchAdapter(private val context: Context,
                        private val list: List<UserEntity>,
                        private val listener: UserSearchAdapterListener) : RecyclerView.Adapter<UserSearchAdapter.ViewHolder>()
{
    interface UserSearchAdapterListener {
        fun OnClickListener(user: UserEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        var view = LayoutInflater.from(context).inflate(R.layout.lay_user_search_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val entity = list[position]
        holder.txtName.text = entity.username
        holder.txtRank.text = context.getString(R.string.app_user_rank, entity.leaderboardPosition)
        holder.txtBestLanguage.text = context.getString(R.string.app_user_best_language, entity.leaderboardPosition)
        holder.card_view.setOnClickListener { listener.OnClickListener(entity) }
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val card_view = itemView.card_view
        val txtName = itemView.txtName
        val txtRank = itemView.txtRank
        val txtBestLanguage = itemView.txtBestLanguage
    }
}