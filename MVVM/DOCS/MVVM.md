# Fonaments del Model Reactiu – Explicació Elemental
Aquest document explica pas a pas els cinc pilars bàsics del model reactiu, amb exemples senzills i comentats en Kotlin.
## 1. Fluxos de dades declaratius
> Declares el *què* vols fer amb les dades, no el *com*.
```kotlin
getAiguaDelRiu() // retorna un Flow<Aigua>
    .filter { it.esNeta }
    .map { it.gel() }
    .collect { ampolla -> println("Ampolla plena: $ampolla") }
```
##  2. Propagació automàtica de canvis en cadena
> Quan una dada nova entra, els canvis es propaguen sols.
```kotlin
flowDeTemperatures()
    .filter { it > 30 }
    .map { "Fa calor: $it ºC" }
    .collect { println(it) }
```
## 3. Backpressure (gestió de sobrecàrrega)
> Protegeix el sistema si rep dades més ràpid del que pot processar.
```kotlin
flowDeSensor()
    .buffer(capacity = 10)          // Emmagatzema 10 valors
    .conflate()                     // Només l’últim si va molt ràpid
    .collect { processa(it) }       // Funció lenta
```
## 4. Tractament unificat d’errors i cancel·lació
> Els errors es gestionen dins el flux mateix.
```kotlin
getTemperatures()
    .map { 100 / it } // pot fallar si és 0!
    .catch { e -> println("Error: ${e.message}") }
    .collect { println(it) }
```
També pots cancel·lar el flux automàticament si canvia el context (per exemple, canvi de pantalla).
## 5. Composició funcional de fluxos
> Pots encadenar operadors com peces de Lego.
```kotlin
val temperatura = flowOf(20, 22, 25, 30, 35)

temperatura
    .filter { it >= 30 }
    .map { "ALERTA: $it ºC" }
    .onEach { avisaUsuari(it) }
    .collect { println(it) }
```
## Conclusió
La programació reactiva combina:
- Declarativitat
- Reacció automàtica
- Robustesa
- Escalabilitat
- Simplicitat composable
## Relació amb la Programació Declarativa
El model reactiu **es basa en la programació declarativa**.
En lloc d’indicar pas a pas què ha de fer el sistema (*estil imperatiu*), simplement **declares les operacions que vols aplicar sobre el flux de dades**.  

Exemple:
```kotlin
temperatures.filter { it > 30 }.map { "Fa calor: $it ºC" }
```
Aquesta línia no processa cap dada immediatament. Només indica **què s’ha de fer** quan arribin noves dades. És el sistema qui s’encarrega de tot: observació, filtratge, transformació i emissió.  

# Model Reactiu vs Patró Observer

El model reactiu modern, usat en Android amb Flow o LiveData, es basa en el patró clàssic **Observer**, però adaptat per gestionar **fluxos de dades asincrònics** i de manera **declarativa**.

## Comparació conceptual

| Patró Observer                    | Model Reactiu (`Flow`, `StateFlow`, `LiveData`) |
|----------------------------------|-------------------------------------------------|
| Observador rep notificacions     | Subscriptor rep emissions de dades             |
| Push manual de dades             | Flux automàtic, amb operadors (`map`, `filter`) |
| Tractament sincrònic             | Tractament asincrònic                          |
| Generalment només un valor       | Pot gestionar múltiples valors al llarg del temps |
| No declarat en cadenes           | Declaració funcional en cadena (`.map {}`)     |

## Exemple clàssic: Observer

```kotlin
val liveData = MutableLiveData<String>()

liveData.observe(this) { valor ->
    textView.text = valor
}
```

## Exemple reactiu: Flow + StateFlow

```kotlin
val dades: StateFlow<String> = _dades

LaunchedEffect(Unit) {
    dades.collect { valor ->
        textView.text = valor
    }
}
```

## En resum

> El model reactiu és una **evolució moderna del patró Observer**, més potent i pensat per entorns asincrònics i declaratius.

## 🖼️ Diagrama comparatiu
![Observer vs Reactiu](MVVM_Observer_vs_Reactiu.png)
---
## El model reactiu NO és només un patró
El **model reactiu** no s'ha de confondre amb un simple patró de disseny com l'Observer. Es tracta d'un **paradigma de programació** que:
- Representa les dades com a **fluxos** (streams)
- Propaga automàticament els **canvis**
- Treballa de forma **asincrònica**
- Permet **composar operacions** sobre les dades (`map`, `filter`, `combine`)
- Gestiona el **backpressure** (control de flux si hi ha excés de dades)
### Exemple
A diferència de l'Observer clàssic, que només escolta canvis, un `Flow` permet:
```kotlin
repository.getValors()
    .filter { it.isValid }
    .map { it.transform() }
    .collect { uiState.value = it }
```
Això mostra com es pot **declarar el processament de dades com una cadena funcional** i reactiva.  
# MVVM en Android amb Jetpack Compose

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
---
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
---
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
---
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
---
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
---
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
---
### UsuariForm.kt
```kotlin
val nom = viewModel.nom.value
TextField(value = nom, onValueChange = { viewModel.onNomChange(it) })
Button(onClick = { viewModel.guardar() }) {
    Text("Guardar")
}
```
---
## Versio 2 - Validacio completa amb errors per camp
### UsuariViewModel.kt
```kotlin
data class FormulariUsuari(val nom: String, val email: String, val edat: String)
data class FormulariErrors(val nom: String?, val email: String?, val edat: String?)
val formulari = mutableStateOf(FormulariUsuari("", "", ""))
val errors = mutableStateOf(FormulariErrors(null, null, null))
fun validar() { ... }
```
---
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
---
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
---
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
---
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
---
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
---
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
