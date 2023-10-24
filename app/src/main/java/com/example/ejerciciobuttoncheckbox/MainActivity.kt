package com.example.ejerciciobuttoncheckbox

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.ejerciciobuttoncheckbox.ui.theme.EjercicioButtonCheckBoxTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//Se necesita:
// implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjercicioButtonCheckBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Principal(lifecycleScope)
                }
            }
        }
    }
}

@Composable
fun BotonPersonalizado(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun FilaCheckBox(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Activar")
    }
}

@Composable
fun FilaSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(text = "Mostrar opciones", modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
fun RadioButtonPersonalizado(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(text = text)
    }
}

@Composable
fun BotonCambiarImagen(image: Int, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        Button(
            onClick = onClick
        ) {
            Text(text = "Cambiar Imagen")
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Principal(scope: LifecycleCoroutineScope) {
    var textoBoton by remember { mutableStateOf("Presionar") }
    var textoOculto by remember { mutableStateOf(false) }
    var estadoSwitch by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("Opcion 1") }
    var casosImage by remember { mutableStateOf(R.drawable.image1) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BotonPersonalizado(textoBoton) {
                textoBoton = if (textoBoton == "Presionar") "Botón Presionado" else "Presionar"
            }


            if (textoBoton == "Botón Presionado") {
                scope.launch {
                    delay(5000)
                    textoBoton = "Presionar"
                }
                CircularProgressIndicator(color = Color.Red)
            }

            FilaCheckBox(textoOculto) {
                textoOculto = !textoOculto
            }

            if (textoOculto) {
                Text(
                    text = "Hola, estaba oculto. ¡Espero que me veas!",
                    modifier = Modifier.padding(8.dp)
                )
            }

            Icon(
                imageVector = Icons.Rounded.ThumbUp,
                contentDescription = "IconThumbUp",
                tint = Color.Blue,
                modifier = Modifier.size(48.dp)
            )

            FilaSwitch(estadoSwitch) {
                estadoSwitch = !estadoSwitch
            }

            if (estadoSwitch) {
                Column {
                    RadioButtonPersonalizado("Opcion 1", selected == "Opcion 1") {
                        selected = "Opcion 1"
                    }

                    RadioButtonPersonalizado("Opcion 2", selected == "Opcion 2") {
                        selected = "Opcion 2"
                    }

                    RadioButtonPersonalizado("Opcion 3", selected == "Opcion 3") {
                        selected = "Opcion 3"
                    }
                }
            }

            BotonCambiarImagen(casosImage) {
                casosImage = when (casosImage) {
                    R.drawable.image1 -> R.drawable.image2
                    R.drawable.image2 -> R.drawable.image3
                    else -> R.drawable.image1
                }
            }

            Image(
                painter = painterResource(id = casosImage),
                contentDescription = "Imagen",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

@Composable
fun CircularProgress(scope: LifecycleCoroutineScope){
    var show by rememberSaveable {
        mutableStateOf(false);
    }
}

















