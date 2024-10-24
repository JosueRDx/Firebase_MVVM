package com.tecsup.firebase.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.tecsup.firebase.model.Contacto

class ContactoViewModel : ViewModel() {

    private val _contactos = MutableLiveData<List<Contacto>>()
    val contactos: LiveData<List<Contacto>> = _contactos

    private val db = FirebaseDatabase.getInstance().getReference("Contacto")

    init {
        loadContactos()
    }

    private fun loadContactos() {
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contactList = mutableListOf<Contacto>()
                for (contactSnapshot in snapshot.children) {
                    val contacto = contactSnapshot.getValue(Contacto::class.java)
                    contacto?.let { contactList.add(it) }
                }
                _contactos.value = contactList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error al leer los contactos: ${error.message}")
            }
        })
    }

    // Insertar
    fun insertContacto(nomCto: String, celCto: String) {
        val idCto = db.push().key ?: return
        val contacto = Contacto(idCto, nomCto, celCto)
        db.child(idCto).setValue(contacto)
    }

    // Actualizar
    fun updateContacto(idCto: String, nomCto: String, celCto: String) {
        val contacto = Contacto(idCto, nomCto, celCto)
        db.child(idCto).setValue(contacto)
    }

    // Eliminar
    fun deleteContacto(idCto: String) {
        db.child(idCto).removeValue()
    }
}
