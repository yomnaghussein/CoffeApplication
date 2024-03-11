package com.example.coffeapplication.ui.compose_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.coffeapplication.domain.model.CoffeeItem
import com.example.coffeapplication.ui.presentation.CoffeeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeDetailsScreen(
    coffeeId: Int,
    navController: NavHostController,
    coffeeViewModel: CoffeeViewModel = CoffeeViewModel()
) {

    val coffeeSelectedId = navController.currentBackStackEntry?.arguments?.getInt("coffeeId") ?: 0

    val coffeeList =
        coffeeViewModel.coffeeListSharedFlow.collectAsState(initial = null).value ?: mutableListOf()
    val selectedCoffeeItem = coffeeList.firstOrNull { it.id.toInt() == coffeeId } ?: CoffeeItem()

    LaunchedEffect(key1 = Unit) {
        coffeeViewModel.getListOfCoffee()
        coffeeViewModel.getCoffeeItem(coffeeSelectedId)
    }


    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Coffee List") },
            colors = TopAppBarDefaults.mediumTopAppBarColors(),
            navigationIcon = { navController.navigateUp() })
    }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CoffeePicture(selectedCoffeeItem, 200.dp)
                CoffeeItemContent(selectedCoffeeItem, Alignment.CenterHorizontally)
            }

        }

    }

}