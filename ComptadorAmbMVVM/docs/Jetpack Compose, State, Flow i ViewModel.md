# Jetpack Compose, State, Flow i ViewModel

## 1. Què és el context i per quin motiu es necessita?

El **context** en Android és un objecte que proporciona accés a recursos i funcionalitats específiques del sistema o de l'app, com ara:

- mostrar un **Toast**  
- accedir a recursos (`getString`, `getDrawable`, etc.)
- llançar activitats o serveis
- accedir a `SharedPreferences`, fitxers, etc.

En el codi:
```kotlin
val context = LocalContext.current
```
`LocalContext.current` obté el `Context` de l’activitat actual, necessari per exemple per fer:

```kotlin
Toast.makeText(context, "Dades guardades", Toast.LENGTH_SHORT).show()
```

---

## 2. Què és el viewModel i per quin motiu generem un viewModel?

El **viewModel** és una classe que separa la lògica de negoci i estat de la UI, seguint el patró **MVVM**.

El generem per:
1. Gestionar l’estat del formulari.
2. Mantenir la lògica fora de la UI.
3. Sobreviure a canvis de configuració.
4. Reutilitzar la lògica en diversos composables.

---

## 3. Entenc que formulari, errors, guardat són variables o objectes del meu viewModel

Correcte. Són valors exposats pel `UsuariViewModel`.

```kotlin
val form = viewModel.formulari.collectAsState().value
val err = viewModel.errors.value
val guardat by viewModel.guardat.collectAsState()
```

- `formulari` és un `StateFlow` o `Flow`
- `errors` és un `MutableState`
- `guardat` és un `StateFlow<Boolean?>`

---

## 4. Quan utilitzar `collectAsState().value`, `.value` sense `collectAsState`, o `collectAsState()` sense `.value`

| Cas                              | Exemple de codi                                                | Quan fer-ho                                 |
|----------------------------------|----------------------------------------------------------------|---------------------------------------------|
| Accedir a `StateFlow`            | `val x = flow.collectAsState().value`                         | Si vols accedir al valor directament        |
| Amb delegació `by`              | `val x by flow.collectAsState()`                              | Si vols evitar `.value`                     |
| Accedir a `MutableState`         | `val x = state.value`                                         | Si és un `mutableStateOf`                   |

---

## 5. Què és `State` i `Flow` i quina diferència hi ha?

| Concepte     | `State` (Compose)               | `StateFlow` (Kotlin Flow)         |
|--------------|----------------------------------|------------------------------------|
| Ús           | Estat a la UI                   | Estat al ViewModel                 |
| Reactiu?     | Sí                              | Sí (amb `collectAsState()`)       |
| Multicast?   | No                              | Sí                                |
| Asíncron?    | No                              | Sí (usa coroutines)               |

---

## 6. Què són coroutines?

Les **coroutines** són una forma eficient d’escriure codi asíncron en Kotlin. Permeten:

- Executar tasques lentes (xarxa, BD...) sense bloquejar la UI
- Fer `suspend` i reprendre el codi més tard
- Evitar callbacks

Exemple:
```kotlin
viewModelScope.launch {
    val dades = repositori.getUsuaris()
    _usuaris.value = dades
}
```

---

## 7. Per quin motiu s'especifica `by` en `val guardat by viewModel.guardat.collectAsState()`?

`by` és una delegació de Kotlin que permet evitar `.value`.

Això:
```kotlin
val guardat by flow.collectAsState()
```

És igual a:
```kotlin
val guardat = flow.collectAsState().value
```

---

## 8. Mini-taula resum sobre `.value`, `by`, `collectAsState`

| Cas                              | Exemple de codi                                                | Quan fer-ho                                 | Notes                                                 |
|----------------------------------|----------------------------------------------------------------|---------------------------------------------|--------------------------------------------------------|
| Accedir a `StateFlow`            | `val x = flow.collectAsState().value`                         | Valor directe                               | Cal `.value`                                          |
| Amb `by`                         | `val x by flow.collectAsState()`                              | Evitar `.value`                             | Més net i llegible                                    |
| Amb `MutableState`               | `val x = state.value`                                         | Estat simple a la UI                        | Sense `collectAsState()`                              |

---

## 9. LaunchedEffect(guardat) { ... } és un subscriber?

Sí. `LaunchedEffect(guardat)` s’executa cada vegada que el valor de `guardat` canvia.

S’utilitza per:
- mostrar toasts
- navegar
- escriure a BD
- crides de xarxa

No redibuixa la UI, només fa efectes col·laterals.
