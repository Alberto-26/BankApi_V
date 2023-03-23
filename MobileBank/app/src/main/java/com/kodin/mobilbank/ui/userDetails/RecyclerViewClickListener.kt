package com.kodin.mobilbank.ui.userDetails

import com.kodin.mobilbank.model.userDetailsBank.UsersDetailsItem

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: Int, items: UsersDetailsItem, position: Int )

    fun onUpdate(position: Int, model: UsersDetailsItem)

    fun onDelete(model: UsersDetailsItem, position: Int)
}