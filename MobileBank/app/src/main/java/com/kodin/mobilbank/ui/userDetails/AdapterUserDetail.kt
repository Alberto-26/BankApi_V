package com.kodin.mobilbank.ui.userDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kodin.mobilbank.R
import com.kodin.mobilbank.databinding.RecyclerviewUserBinding
import com.kodin.mobilbank.model.userDetailsBank.UsersDetailsItem

class AdapterUserDetail (
//    private var listaProductos: ArrayList<Items> = ArrayList(),
private  var listaUsers : ArrayList<UsersDetailsItem> =ArrayList(),
private val listenerUsers: RecyclerViewClickListener

        ) :    RecyclerView.Adapter<HolderUsers> ()   {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderUsers {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HolderUsers(layoutInflater.inflate(R.layout.recyclerview_user,parent,false) )

    }

    override fun getItemCount(): Int {
         return  listaUsers.size
    }

    override fun onBindViewHolder(holder: HolderUsers, position: Int) {
        val user = listaUsers.get(position)
        holder.render(user!!,listenerUsers,position)

    }

}



class HolderUsers(inflate: View)  : RecyclerView.ViewHolder(inflate) {
    val binding = RecyclerviewUserBinding.bind(inflate)
    fun render(userDetail:UsersDetailsItem, listener: RecyclerViewClickListener,position: Int             ){
        binding. userNombre.text =""+userDetail.name

        binding. idUser.text = ""+userDetail.userId


        binding .idMail .text = ""+userDetail.email
        binding.idUser.setOnClickListener {
          listener.onRecyclerViewItemClick(binding.idUser.id,userDetail,position)
        }

    }










}