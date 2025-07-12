
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

