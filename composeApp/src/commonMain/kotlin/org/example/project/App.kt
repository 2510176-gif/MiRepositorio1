package org.example.project

import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun App() {
//Aqui acomodo todas mis variables de estado
    var nombre by remember { mutableStateOf("") }
    var matricula by remember { mutableStateOf("") }
    var asignatura by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("")  }
    var fecha by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    //Aqui estan mis variables de error
    var errorNombre by remember { mutableStateOf(false) }
    var errorMatricula by remember { mutableStateOf(false) }
    var errorAsignatura by remember { mutableStateOf(false) }
    var errorHora by remember { mutableStateOf(false) }
    var errorFecha by remember { mutableStateOf(false) }

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color (0xFFFFE0B2)
    ){}
    //Aqui empieza el codigo

    Text(text = "            Formulario de datos",
    style = TextStyle(
        fontSize = 20.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.size(400.dp,300.dp)
   )



    Column(
        modifier = Modifier.padding(16.dp)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Ingresa tu nombre")

        TextField(
            value = nombre,
            onValueChange = {
                // Se actualiza el estado
                nombre = it
                //Error en el tipo de dato
                errorNombre = false
            },
            modifier = Modifier.size(500.dp,60.dp),
            shape = RoundedCornerShape (12.dp),
            isError = errorNombre,
            placeholder = {Text("Ejemplo: Angel Jesus Escamilla Reyes")}
        )
        if (errorNombre){
            Text("Dato no reconocible o vacio.", color = MaterialTheme.colorScheme.error)
        }

        Text(text = "Hola $nombre")
        Spacer(modifier = Modifier.height(24.dp)) // espacio entre bloques



            //Bloque matrícula
            Text(text = "Ingresa tu matrícula")
            TextField(
                value = matricula, onValueChange = { matricula = it
                    errorMatricula=false },
                modifier = Modifier.size(500.dp, 60.dp),
                shape = RoundedCornerShape(12.dp),
                isError = errorMatricula,
                placeholder = {Text("Ejemplo: 2510176")}
            )
            if (errorMatricula){
            Text("Dato no reconocible o vacio.", color = MaterialTheme.colorScheme.error)
            }

            Text(text = "Tu matricula es $matricula")


        //Bloque asignatura
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Ingresa tu asignatura")
        TextField(value = asignatura, onValueChange = { asignatura = it
            errorAsignatura = false},
            modifier = Modifier.size(500.dp,60.dp),
            shape = RoundedCornerShape (12.dp),
            isError = errorAsignatura,
            placeholder = {Text("Ejemplo: Programación")})
        if (errorAsignatura){
            Text("Dato no reconocible o vacio.", color = MaterialTheme.colorScheme.error)
        }
        Text(text = "La asignatura es $asignatura")


        //Bloque hora
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Ingresa la hora en la que se imparte (HH:MM)")
        TextField(value = hora, onValueChange = {hora = it
            errorHora = false},
            modifier = Modifier.size(500.dp,60.dp),
            shape = RoundedCornerShape (12.dp),
            isError = errorHora,
            placeholder = {Text("Ejemplo: 14:30")})


        if (errorHora){
            Text("La hora debe estar en formato HH:MM (ej. 08:30)", color = MaterialTheme.colorScheme.error)
        }
        Text(text = "La hora que se imparte es $hora")

        //Bloque fecha
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Ingresa la fecha de entrega (DD/MM/YYYY)")
        TextField(value = fecha, onValueChange = {fecha = it
            errorFecha = false},
            modifier = Modifier.size(500.dp,60.dp),
            shape = RoundedCornerShape (12.dp),
            isError = errorFecha,
            placeholder = {Text("Ejemplo: 31/01/2026")})
        if (errorFecha){
            Text("La fecha debe estar en formato DD/MM/YYYY.", color = MaterialTheme.colorScheme.error)
        }
        Text(text = "La fecha de entrega es $fecha")

        Button(
            onClick ={
                val soloLetrasnombre = nombre.all {it.isLetter()|| it.isWhitespace()}
                errorNombre = nombre.isBlank() || !soloLetrasnombre
                val matriculaInt = matricula.toIntOrNull()
                errorMatricula = matriculaInt == null
                val soloLetrasAsignatura = asignatura.all {it.isLetter()||it.isWhitespace()}
                errorAsignatura = asignatura.isBlank() || !soloLetrasAsignatura
                val horaValida = hora.matches(Regex("^([01]?\\d|2[0-3]):[0-5]\\d$"))
                errorHora = !horaValida
                val fechaValida = fecha.matches(Regex("^([0-2]?\\d|3[01])/([0]?\\d|1[0-2])/\\d{4}$"))
                errorFecha = !fechaValida

                //Si no hay errores mostrará una ventana emergente
                if (!errorNombre && !errorMatricula && !errorAsignatura && !errorHora && !errorFecha) {
                    showDialog = true
                }
            }
        ){
            Text("Guardar")
        }
    }
if (showDialog) {
    AlertDialog(
        onDismissRequest = { showDialog = false},
        confirmButton = {
            Button(onClick = {showDialog = false}) {
                Text("OK")
            }
        },
        title = {Text("Validacion exitosa")},
        text = {Text ("Tus datos han sido validados correctamente")}
    )
}

}