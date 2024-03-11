package com.example.coffeapplication.ui.compose_screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.coffeapplication.domain.model.CoffeeItem
import com.example.coffeapplication.ui.presentation.CoffeeViewModel
import com.google.accompanist.coil.rememberCoilPainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeListScreen(
    coffeeViewModel: CoffeeViewModel = CoffeeViewModel(),
    navController: NavHostController
) {


    val coffeeList =
        coffeeViewModel.coffeeListSharedFlow.collectAsState(initial = null).value ?: mutableListOf()


    LaunchedEffect(key1 = Unit) {
        coffeeViewModel.getListOfCoffee()
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Coffee List")
            }, colors = TopAppBarDefaults.mediumTopAppBarColors()
        )
    }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            LazyColumn(contentPadding = it, content =
            {
                items(coffeeList.size) {
                    CoffeeItemView(coffeeItem =coffeeList[it]) {
                        navController.navigate("coffeeDetails/${it.id}")
                        Log.d("CoffeeClick"," coffeeId${it.id}")
                    }

                }
            })
        }
    }
}

@Composable
fun CoffeeItemView(coffeeItem: CoffeeItem, clickAction: (CoffeeItem) -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable { clickAction.invoke(coffeeItem) },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            CoffeePicture(coffeeItem, 70.dp)
            CoffeeItemContent(coffeeItem = coffeeItem, alignment = Alignment.Start)
        }
    }
}

@Composable
fun CoffeePicture(coffeeItem: CoffeeItem, profilePicSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onPrimaryContainer

        ),
        modifier = Modifier.padding(16.dp)

    ) {
        Image(
            painter = rememberCoilPainter(coffeeItem.image),
            contentDescription = "",
            modifier = Modifier.size(profilePicSize),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun CoffeeItemContent(coffeeItem: CoffeeItem, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {

        Text(text = coffeeItem.title, style = MaterialTheme.typography.bodyLarge)
        Text(
            text = coffeeItem.description,
            style = MaterialTheme.typography.bodyMedium
        )

    }
}
