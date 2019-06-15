package br.com.wsilva.codewars.features.userdetail.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.codewars.R
import br.com.wsilva.codewars.model.entity.UserLanguagesEntity
import kotlinx.android.synthetic.main.lay_user_detail_home_language_adapter.view.*

class UserDetailHomeLanguageAdapter(private val context: Context,
                                    private val list: List<UserLanguagesEntity>,
                                    private val listener: UserDetailHomeLanguageAdapterListener) : RecyclerView.Adapter<UserDetailHomeLanguageAdapter.ViewHolder>()
{
    interface UserDetailHomeLanguageAdapterListener {
        fun OnClickListener(userLanguagesEntity: UserLanguagesEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        var view = LayoutInflater.from(context).inflate(R.layout.lay_user_detail_home_language_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val entity = list[position]
        holder.lblLanguage.text = entity.language
        holder.lblRank.text = context.getString(R.string.app_user_rank, entity.rank)
        holder.lblScore.text = context.getString(R.string.app_score, entity.score)
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val lblLanguage = itemView.lblLanguage
        val lblRank = itemView.lblRank
        val lblScore = itemView.lblScore
    }
}