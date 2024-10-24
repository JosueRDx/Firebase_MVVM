package com.tecsup.firebase.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tecsup.firebase.viewmodel.ContactoViewModel

@Composable
fun MainScreenFirebase(viewModel: ContactoViewModel = viewModel()) {
    var idCto by remember { mutableStateOf("") }
    var nomCto by remember { mutableStateOf("") }
    var celCto by remember { mutableStateOf("") }

    val listCto by viewModel.contactos.observeAsState(emptyList())

    Column(
        Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(35.dp))
        Text(
            "APLICACION CON FIREBASE",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            "Contactos FDS",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        TextField(
            value = idCto,
            onValueChange = { idCto = it },
            label = { Text("ID:") },
            readOnly = true
        )
        TextField(
            value = nomCto,
            onValueChange = { nomCto = it },
            label = { Text("Nombre Contacto:") }
        )
        TextField(
            value = celCto,
            onValueChange = { celCto = it },
            label = { Text("Nro Celular Contacto:") }
        )

        Row {
            Button(onClick = {
                viewModel.insertContacto(nomCto, celCto)
                nomCto = ""
                celCto = ""
            }) {
                Text("Insert")
            }
            Spacer(Modifier.width(12.dp))
            Button(onClick = {
                viewModel.updateContacto(idCto, nomCto, celCto)
                idCto = ""
                nomCto = ""
                celCto = ""
            }) {
                Text("Update")
            }
            Spacer(Modifier.width(12.dp))
            Button(onClick = {
                viewModel.deleteContacto(idCto)
                idCto = ""
                nomCto = ""
                celCto = ""
            }) {
                Text("Delete")
            }
        }

        Text(
            "LISTADO DE CONTACTOS",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        LazyColumn {
            items(listCto) { contacto ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(contacto.nomCto, Modifier.weight(0.3f), fontWeight = FontWeight.Bold)
                    Text(contacto.celCto, Modifier.weight(0.2f), fontWeight = FontWeight.Bold)
                    IconButton(onClick = {
                        idCto = contacto.idCto
                        nomCto = contacto.nomCto
                        celCto = contacto.celCto
                    }) {
                        Icon(imageVector = Icons.Outlined.Edit, contentDescription = "Editar")
                    }
                }
            }
        }
    }
}
