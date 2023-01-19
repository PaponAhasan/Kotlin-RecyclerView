package com.example.kotlinrecyclerview

import java.io.Serializable

/*
Ref:

https://picsum.photos/
 */
data class Contact(val name : String, val age : Int) : Serializable {
    val imageUrl = "https://picsum.photos/200?random=$age"
    val landscapeImageUrl = "https://picsum.photos/300/150?random=$age"
}