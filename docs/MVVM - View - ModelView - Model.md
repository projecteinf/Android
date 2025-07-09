# MVVM Android Project

## MVVM en Android amb Jetpack Compose

El patro MVVM (Model-View-ViewModel) separa les responsabilitats de l'aplicacio en tres capes:
- Model: acces i gestio de dades (Room, API, DataStore...)
- ViewModel: logica de negoci i estat observable per la vista
- View: la interficie grafica, observa l'estat i envia accions al ViewModel
Aquest patro permet una aplicacio modular, testejable i escalable.
1. Formulari senzill amb guardar()
- Nomes un camp: nom
- ViewModel valida que no sigui buit
- Mostra Toast si sha guardat o no
```kotlin
// ViewModel
var nom = mutableStateOf("")
fun guardar() {
    if (nom.value.isNotBlank()) { repo.guardar(nom.value) }
}
// View
TextField(value = nom, onValueChange = { nom = it })
Button(onClick = { viewModel.guardar() }) { Text("Guardar") }
```
2. Formulari amb validacio completa
- Camps: nom, email, edat
- Validacions amb missatges d'error
- Boto nomes actiu si tot es valid
```kotlin
// ViewModel
val errors = mutableStateOf(FormulariErrors())
val formulari = mutableStateOf(FormulariUsuari())
fun validar() { ... }
// View
TextField(isError = errors.nom != null)
if (errors.nom != null) Text(errors.nom)
```
3. Lectura inicial via init
- ViewModel carrega dades simulades al init
- Formulari mostra valors inicials
```kotlin
init {
    formulari.value = repo.obtenir()
}
```
4. Lectura reactiva amb Flow
- Model exposa un Flow
- ViewModel recull amb collect i exposa StateFlow
- View mostra dades en temps real amb collectAsState()
```kotlin
// Repository
val dades = MutableStateFlow<FormulariUsuari?>(...)
val fluxFormulari: Flow<FormulariUsuari?> = dades
// ViewModel
val formulari: StateFlow<FormulariUsuari?> = 
    repo.fluxFormulari.stateIn(scope, ..., initialValue = null)
```
## Fitxers del projecte MVVM - Versio Reactiva amb Flow
### MainActivity.kt
```kotlin
package cat.mba.nomapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface { UsuariForm() }
            }
        }
    }
}
```
### FormulariUsuari.kt
```kotlin
package cat.mba.nomapp
data class FormulariUsuari(
    val nom: String = "",
    val email: String = "",
    val edat: String = ""
)

data class FormulariErrors(

    val nom: String? = null,
    val email: String? = null,
    val edat: String? = null
)
```
### UsuariRepository.kt
```kotlin
package cat.mba.nomapp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class UsuariRepository {
    private val dades = MutableStateFlow<FormulariUsuari?>(FormulariUsuari(
        nom = "Maria",
        email = "maria@example.com",
        edat = "29"
    ))
    val fluxFormulari: Flow<FormulariUsuari?> = dades
    fun guardar(f: FormulariUsuari) {
        dades.value = f
    }
}
```
### UsuariViewModel.kt
```kotlin
package cat.mba.nomapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UsuariViewModel : ViewModel() {
    private val repo = UsuariRepository()
    val formulari: StateFlow<FormulariUsuari?> = repo.fluxFormulari.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )
    var errors = mutableStateOf(FormulariErrors())
        private set
    private val _guardat = MutableStateFlow<Boolean?>(null)
    val guardat: StateFlow<Boolean?> = _guardat

    fun onNomChange(nouNom: String) {
        formulari.value?.let {
            repo.guardar(it.copy(nom = nouNom))
            validar()
        }
    }

    fun onEmailChange(nouEmail: String) {
        formulari.value?.let {
            repo.guardar(it.copy(email = nouEmail))
            validar()
        }
    }

    fun onEdatChange(novaEdat: String) {
        formulari.value?.let {
            repo.guardar(it.copy(edat = novaEdat))
            validar()
        }
    }

    private fun validar() {
        val f = formulari.value ?: return
        val edatNum = f.edat.toIntOrNull()
        errors.value = FormulariErrors(
            nom = if (f.nom.isBlank()) "El nom no pot estar buit" else null,
            email = if (!f.email.contains("@")) "Email invalid" else null,
            edat = when {
                f.edat.isBlank() -> "L'edat es obligatoria"
                edatNum == null -> "Ha de ser un numero"
                edatNum < 18 -> "Minim 18 anys"
                else -> null
            }
        )
    }

    fun guardar() {
        if (errors.value == FormulariErrors()) {
            _guardat.value = true
        } else {
            _guardat.value = false
        }
    }

    fun resetGuardat() {
        _guardat.value = null
    }
}
```
### UsuariForm.kt
```kotlin
package cat.mba.nomapp

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UsuariForm(viewModel: UsuariViewModel = viewModel()) {
    val context = LocalContext.current
    val f = viewModel.formulari.collectAsState().value
    val err = viewModel.errors.value
    val guardat by viewModel.guardat.collectAsState()
    LaunchedEffect(guardat) {
        when (guardat) {
            true -> Toast.makeText(context, "Dades guardades!", Toast.LENGTH_SHORT).show()
            false -> Toast.makeText(context, "Hi ha errors", Toast.LENGTH_SHORT).show()
            null -> {}
        }
        viewModel.resetGuardat()
    }
    if (f == null) {
        CircularProgressIndicator()
        return
    }
    Column(Modifier.padding(16.dp)) {
        TextField(
            value = f.nom,
            onValueChange = viewModel::onNomChange,
            label = { Text("Nom") },
            isError = err.nom != null
        )
        if (err.nom != null) Text(err.nom, color = MaterialTheme.colorScheme.error)
        Spacer(Modifier.height(8.dp))
        TextField(
            value = f.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("Email") },
            isError = err.email != null
        )
        if (err.email != null) Text(err.email, color = MaterialTheme.colorScheme.error)
        Spacer(Modifier.height(8.dp))
        TextField(
            value = f.edat,
            onValueChange = viewModel::onEdatChange,
            label = { Text("Edat") },
            isError = err.edat != null
        )
        if (err.edat != null) Text(err.edat, color = MaterialTheme.colorScheme.error)
        Spacer(Modifier.height(16.dp))
        Button(onClick = viewModel::guardar) {
            Text("Guardar")
        }
    }
}
```
## MVVM Android - Fitxers per cada versio
## Versio 1 - Formulari simple amb guardar()
### UsuariViewModel.kt
```kotlin
var nom = mutableStateOf("")
fun guardar() {
    if (nom.value.isNotBlank()) {
        UsuariRepository.guardarNom(nom.value)
    }
}
```
### UsuariForm.kt
```kotlin
val nom = viewModel.nom.value
TextField(value = nom, onValueChange = { viewModel.onNomChange(it) })
Button(onClick = { viewModel.guardar() }) {
    Text("Guardar")
}
```
## Versio 2 - Validacio completa amb errors per camp
### UsuariViewModel.kt
```kotlin
data class FormulariUsuari(val nom: String, val email: String, val edat: String)
data class FormulariErrors(val nom: String?, val email: String?, val edat: String?)
val formulari = mutableStateOf(FormulariUsuari("", "", ""))
val errors = mutableStateOf(FormulariErrors(null, null, null))
fun validar() { ... }
```
### UsuariForm.kt
```kotlin
TextField(value = f.nom, onValueChange = viewModel::onNomChange, isError = err.nom !=
null)
if (err.nom != null) Text(err.nom)
```
## Versio 3 - Carregar dades inicials via init
### UsuariViewModel.kt
```kotlin
init {
    formulari.value = UsuariRepository.obtenir()
}
```
## Versio 4 - Lectura reactiva amb Flow
### MainActivity.kt
```kotlin
package cat.mba.nomapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface { UsuariForm() }
            }
        }
    }
}
```
### FormulariUsuari.kt
```kotlin
package cat.mba.nomapp

data class FormulariUsuari(
    val nom: String = "",
    val email: String = "",
    val edat: String = ""
)
data class FormulariErrors(
    val nom: String? = null,
    val email: String? = null,
    val edat: String? = null
)
```
### UsuariRepository.kt
```kotlin
package cat.mba.nomapp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class UsuariRepository {
    private val dades = MutableStateFlow<FormulariUsuari?>(FormulariUsuari(
        nom = "Maria",
        email = "maria@example.com",
        edat = "29"
    ))

    val fluxFormulari: Flow<FormulariUsuari?> = dades

    fun guardar(f: FormulariUsuari) {
        dades.value = f
    }
}
```
### UsuariViewModel.kt
```kotlin
package cat.mba.nomapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UsuariViewModel : ViewModel() {
    private val repo = UsuariRepository()
    val formulari: StateFlow<FormulariUsuari?> = repo.fluxFormulari.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )
    var errors = mutableStateOf(FormulariErrors())
        private set
    private val _guardat = MutableStateFlow<Boolean?>(null)
    val guardat: StateFlow<Boolean?> = _guardat
    fun onNomChange(nouNom: String) {
        formulari.value?.let {
            repo.guardar(it.copy(nom = nouNom))
            validar()
        }
    }

    fun onEmailChange(nouEmail: String) {
        formulari.value?.let {
            repo.guardar(it.copy(email = nouEmail))
            validar()
        }
    }

    fun onEdatChange(novaEdat: String) {
        formulari.value?.let {
            repo.guardar(it.copy(edat = novaEdat))
            validar()
        }
    }

    private fun validar() {
        val f = formulari.value ?: return
        val edatNum = f.edat.toIntOrNull()
        errors.value = FormulariErrors(
            nom = if (f.nom.isBlank()) "El nom no pot estar buit" else null,
            email = if (!f.email.contains("@")) "Email invalid" else null,
            edat = when {
                f.edat.isBlank() -> "L'edat es obligatoria"
                edatNum == null -> "Ha de ser un numero"
                edatNum < 18 -> "Minim 18 anys"
                else -> null
            }
        )
    }

    fun guardar() {
        if (errors.value == FormulariErrors()) {
            _guardat.value = true
        } else {
            _guardat.value = false
        }
    }

    fun resetGuardat() {
        _guardat.value = null
    }
}
```
### UsuariForm.kt
```kotlin
package cat.mba.nomapp

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UsuariForm(viewModel: UsuariViewModel = viewModel()) {
    val context = LocalContext.current
    val f = viewModel.formulari.collectAsState().value
    val err = viewModel.errors.value
    val guardat by viewModel.guardat.collectAsState()
    LaunchedEffect(guardat) {
        when (guardat) {
            true -> Toast.makeText(context, "Dades guardades!", Toast.LENGTH_SHORT).show()
            false -> Toast.makeText(context, "Hi ha errors", Toast.LENGTH_SHORT).show()
            null -> {}
        }
        viewModel.resetGuardat()
    }
    if (f == null) {
        CircularProgressIndicator()
        return
    }
    Column(Modifier.padding(16.dp)) {
        TextField(
            value = f.nom,
            onValueChange = viewModel::onNomChange,
            label = { Text("Nom") },
            isError = err.nom != null
        )
        if (err.nom != null) Text(err.nom, color = MaterialTheme.colorScheme.error)
        Spacer(Modifier.height(8.dp))
        TextField(
            value = f.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("Email") },
            isError = err.email != null
        )
        if (err.email != null) Text(err.email, color = MaterialTheme.colorScheme.error)
        Spacer(Modifier.height(8.dp))
        TextField(
            value = f.edat,
            onValueChange = viewModel::onEdatChange,
            label = { Text("Edat") },
            isError = err.edat != null
        )
        if (err.edat != null) Text(err.edat, color = MaterialTheme.colorScheme.error)
        Spacer(Modifier.height(16.dp))
        Button(onClick = viewModel::guardar) {
            Text("Guardar")
        }
    }
}
```

