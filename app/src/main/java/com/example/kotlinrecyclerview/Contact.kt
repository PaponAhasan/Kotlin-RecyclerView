package com.example.kotlinrecyclerview

/*
Ref:

https://picsum.photos/

 */
data class Contact(val name : String, val age : Int){
    val imageUrl = "https://picsum.photos/200?random=$age"
}


//class Contact(val name: String, val age: Int) {
//    companion object {
//        fun createContactsList(numContacts: Int): List<Contact> {
//            val contacts = mutableListOf<Contact>()
//            for (i in 1..numContacts) {
//                contacts.add(Contact("Person #$i", i))
//            }
//            return contacts
//        }
//    }
//}