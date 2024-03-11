package com.example.coffeapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
data class CoffeeItem(val id: String = "",
                      val title: String = "",
                      val description: String = "",
                      val ingredients: MutableList<String> = mutableListOf(),
                      val image: String = ""): Parcelable

