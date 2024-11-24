package com.example.taller4

import com.google.firebase.firestore.FirebaseFirestore

class ItemRepository {

    private val db = FirebaseFirestore.getInstance()
    private val itemsCollection = db.collection("items")

    // Añadir un nuevo elemento a Firestore
    fun addItem(itemName: String) {
        val itemData = hashMapOf("name" to itemName)
        itemsCollection.add(itemData)
            .addOnSuccessListener {
                // Elemento añadido correctamente
            }
            .addOnFailureListener {
                // Manejo de errores si falla la adición
            }
    }

    // Obtener todos los elementos de Firestore (podrías usar esto para actualizar el widget)
    fun getItems(callback: (List<String>) -> Unit) {
        itemsCollection.get()
            .addOnSuccessListener { result ->
                val items = result.map { document -> document.getString("name") ?: "" }
                callback(items)
            }
            .addOnFailureListener {
                // Manejo de errores si falla la obtención
                callback(emptyList())
            }
    }
}
