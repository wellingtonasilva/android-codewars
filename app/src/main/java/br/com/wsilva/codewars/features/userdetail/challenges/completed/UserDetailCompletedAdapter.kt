package br.com.wsilva.codewars.features.userdetail.challenges.completed

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.codewars.R
import br.com.wsilva.codewars.model.entity.UserChallengeEntity
import kotlinx.android.synthetic.main.lay_user_detail_completed_adapter.view.*

class UserDetailCompletedAdapter(private val context: Context,
                                 private val list: List<UserChallengeEntity>,
                                 private val listener: UserDetailCompletedAdapterListener) : RecyclerView.Adapter<UserDetailCompletedAdapter.ViewHolder>()
{
    interface UserDetailCompletedAdapterListener {
        fun OnClickListener(userChallengeEntity: UserChallengeEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        var view = LayoutInflater.from(context).inflate(R.layout.lay_user_detail_completed_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val entity = list[position]
        holder.lblTitle.text = entity.name
        holder.lblCompletedLanguages.text = ""
        holder.lblCompletedAt.text = context.getString(R.string.app_completed_at, entity.completedAt)
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val lblTitle = itemView.lblTitle
        val lblCompletedLanguages = itemView.lblCompletedLanguages
        val lblCompletedAt = itemView.lblCompletedAt
    }
}