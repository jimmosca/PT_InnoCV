package com.jca.myapplication.ui.main_screen



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jca.myapplication.R
import com.jca.myapplication.model.User


class UserAdapter(private val presenter: MainPresenter) : RecyclerView.Adapter<MovieViewHolder>(){

    private var userList = listOf<User>()

    fun addUsers(users: List<User>) {
        userList = users
        notifyDataSetChanged()
    }

    override fun getItemCount() = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {


        return MovieViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = userList[position]

        holder.bind(currentMovie, presenter)

    }

}

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val txtName: TextView = view.findViewById(R.id.txtName)
    private val txtBirthDate: TextView = view.findViewById(R.id.txtBirthDate)
    private val btnEdit: ImageView = view.findViewById(R.id.btnEdit)
    private val btnDelete: ImageView = view.findViewById(R.id.btnDelete)

    fun bind(user: User, presenter: MainPresenter) {
        txtName.text = user.name
        txtBirthDate.text = user.birthdate
        btnEdit.setOnClickListener { presenter.onEditClicked(user.id) }
        btnDelete.setOnClickListener { presenter.onDeleteClicked(user.id) }

    }
    companion object{
        fun from(parent: ViewGroup): MovieViewHolder {
            val movieItem = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
            return MovieViewHolder(movieItem)
        }
    }

}