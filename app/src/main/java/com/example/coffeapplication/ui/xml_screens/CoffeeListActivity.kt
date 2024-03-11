package com.example.coffeapplication.ui.xml_screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeapplication.R
import com.example.coffeapplication.ui.presentation.CoffeeViewModel
import kotlinx.coroutines.launch

class CoffeeListActivity : AppCompatActivity() {

    private val viewModel: CoffeeViewModel by viewModels()
    private lateinit var coffeeListRv:  RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee_list)
        coffeeListRv = findViewById(R.id.rvCoffeeList)
        coffeeListRv.setLayoutManager(LinearLayoutManager(this@CoffeeListActivity))

        viewModel.getListOfCoffee()
        collectList(coffeeListRv)

    }

    private fun collectList(coffeeListRv: RecyclerView) {
        lifecycleScope.launch {
            viewModel.coffeeListSharedFlow.collect {
                it?.let { coffeeItems ->
                    coffeeListRv.adapter = CoffeeListAdapter(coffeeItems)
                    { position ->
                        val intent =
                            Intent(this@CoffeeListActivity, CoffeeDetailsActivity::class.java)
                        intent.putExtra("coffeeDetails", coffeeItems[position])
                        startActivity(intent)
                    }
                }

            }
        }

    }
}