package com.example.coffeapplication.ui.xml_screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeapplication.R
import com.example.coffeapplication.domain.model.CoffeeItem
import com.squareup.picasso.Picasso

class CoffeeListAdapter(
    private val listOfCoffee: MutableList<CoffeeItem>,
    val onClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<CoffeeListAdapter.CoffeeListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.coffee_item, parent, false)

        return CoffeeListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfCoffee.size
    }

    override fun onBindViewHolder(holder: CoffeeListViewHolder, position: Int) {
        holder.bindData(listOfCoffee[position])
    }

    inner class CoffeeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(coffeeItem: CoffeeItem) {
            val coffeeItemContainer = itemView.findViewById<RelativeLayout>(R.id.rlCoffeeItem)
            val coffeeImage = itemView.findViewById<ImageView>(R.id.ivCoffeeIcon)
            val coffeeTitle = itemView.findViewById<TextView>(R.id.tvCoffeeTitle)
            val coffeeDesc = itemView.findViewById<TextView>(R.id.tvCoffeeDescription)


            coffeeItemContainer.setOnClickListener { onClick.invoke(adapterPosition) }
            coffeeTitle.text = coffeeItem.title
            coffeeDesc.text = coffeeItem.description
            Picasso.get().load(coffeeItem.image).into(coffeeImage)
        }
    }
}