package com.example.coffeapplication.ui.xml_screens

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeapplication.R
import com.example.coffeapplication.domain.model.CoffeeItem
import com.squareup.picasso.Picasso

@Suppress("DEPRECATION")
class CoffeeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee_details)

        val details = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("coffeeDetails", CoffeeItem::class.java)
        } else {
            intent.getParcelableExtra("coffeeDetails")
        }

        setDetailsToScreen(details)
    }

    private fun setDetailsToScreen(details: CoffeeItem?) {
        val coffeeImage = findViewById<ImageView>(R.id.ivCoffeeIcon)
        val coffeeTitle = findViewById<TextView>(R.id.tvCoffeeTitle)
        val coffeeDesc = findViewById<TextView>(R.id.tvCoffeeDescription)
        val coffeeIngredients = findViewById<TextView>(R.id.tvIngredients)


        coffeeTitle.text = details?.title
        coffeeDesc.text = details?.description
        if (details != null) {
            Picasso.get().load(details.image).into(coffeeImage)
        }

        var appendedString = ""
        details?.ingredients?.forEach {
            appendedString = appendedString.plus("$it \n")
        }
        coffeeIngredients.text = appendedString
    }
}
